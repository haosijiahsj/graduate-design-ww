package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zzz.dao.*;
import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import com.zzz.model.po.*;
import com.zzz.model.vo.*;
import com.zzz.service.RoomService;
import com.zzz.support.PageResult;
import com.zzz.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by  on 12/2 0002.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl implements RoomService {

    private static final long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
    private static final int SCALE = 1;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private RoomBookRepository roomBookRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private CommodityBookRepository commodityBookRepository;

    @Override
    public PageResult<RoomVo> findAllRoom(Pageable pageable) {
        Preconditions.checkNotNull(pageable, "入参pageable不能为空！");

        Page<RoomPo> roomPoPage = roomRepository.findAll(pageable);

        return this.convertPage(roomPoPage);
    }

    @Override
    public PageResult<RoomVo> findAllRoomByStatus(RoomStatus status, Pageable pageable) {
        Preconditions.checkNotNull(status, "入参status不能为空！");
        Preconditions.checkNotNull(pageable, "入参pageable不能为空！");

        Page<RoomPo> roomPoPage = roomRepository.findByStatus(status, pageable);

        return ConvertUtils.convertPage(roomPoPage, RoomVo.class);
    }

    @Override
    public Map<String, Integer> countCanBookRoomNumByType() {
        List<RoomPo> roomPos = roomRepository.findByStatus(RoomStatus.CAN_BOOK);

        Map<String, Integer> map = Maps.newHashMap();

        if (CollectionUtils.isEmpty(roomPos)) {
            Arrays.stream(RoomType.values()).forEach(roomType -> map.put(roomType.getName(), 0));
            return map;
        }

        Set<String> dbSet = Sets.newHashSet();
        roomPos.stream()
                .collect(Collectors.groupingBy(RoomPo::getType, Collectors.counting()))
                .forEach(((roomType, num) -> {
                        map.put(roomType.getName(), num.intValue());
                        dbSet.add(roomType.getName());
                }));

        Set<String> allSet = Arrays.stream(RoomType.values()).map(RoomType::getName).collect(Collectors.toSet());
        Set<String> diffSet = Sets.difference(allSet, dbSet);

        if (CollectionUtils.isEmpty(diffSet)) {
            return map;
        }
        diffSet.forEach(typeStr -> map.put(typeStr, 0));

        return map;
    }

    @Override
    public void bookRoom(RoomBookVo roomBookVo, ConsumerVo consumerVo) {
        Preconditions.checkNotNull(roomBookVo, "入参rookBookVo不能为空！");
        Preconditions.checkNotNull(consumerVo, "入参consumerVo不能为空！");

        ConsumerPo consumerPo = new ConsumerPo();
        RoomBookPo roomBookPo = new RoomBookPo();
        BeanUtils.copyProperties(consumerVo, consumerPo);
        BeanUtils.copyProperties(roomBookVo, roomBookPo);

        consumerRepository.save(consumerPo);

        roomBookPo.setConsumer(consumerPo.getId());
        roomBookRepository.save(roomBookPo);

        roomRepository.updateStatusById(RoomStatus.CAN_NOT_BOOK, roomBookPo.getRoom());
    }

    @Override
    public void updateRoom(RoomVo roomVo) {
        Preconditions.checkNotNull(roomVo, "入参roomVo不能为空！");

        roomRepository.update(roomVo.getPrice(), roomVo.getStatus(), roomVo.getType(), roomVo.getId());
    }

    @Override
    public RoomBookVo settleRoom(Integer id) {
        Preconditions.checkNotNull(id, "入参id不能为空！");

        RoomBookVo roomBookVo = new RoomBookVo();
        BeanUtils.copyProperties(roomBookRepository.getById(id), roomBookVo);
        List<CommodityBookPo> commodityBookPos = commodityBookRepository.findByRoomBook(id);

        if (CollectionUtils.isEmpty(commodityBookPos)) {
            // 没有消费商品，退定金
            roomBookVo.setSettlementPrice(BigDecimal.ZERO.subtract(roomBookVo.getDeposit()));
            log.info("房间预定单id：{}没有消费，退还定金：{}", id, roomBookVo.getDeposit());
            return roomBookVo;
        }

        // 计算消费总金额
        BigDecimal commodityPrice = commodityBookPos.stream()
                .map(commodityBookPo -> {
                    CommodityPo commodityPo = commodityRepository.getById(commodityBookPo.getCommodity());
                    int num = commodityBookPo.getNum() == null ? 0 : commodityBookPo.getNum();
                    return commodityPo.getPrice().multiply(BigDecimal.valueOf(num));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        roomBookVo.setSettlementPrice(roomBookVo.getDeposit().subtract(commodityPrice));
        log.info("房间预定单id：{}，计算价格：{}", id, roomBookVo.getSettlementPrice());

        return roomBookVo;
    }

    @Override
    public void updateRoomForSettle(RoomBookVo roomBookVo) {
        Preconditions.checkNotNull(roomBookVo, "入参roomBookVo不能为空！");

        log.info("更新房间id：{}状态未可预订，房间单id：{}计算价格", roomBookVo.getRoom(), roomBookVo.getId());
        roomRepository.updateStatusById(RoomStatus.CAN_NOT_BOOK, roomBookVo.getRoom());
        roomBookRepository.updateSettlementPrice(roomBookVo.getSettlementPrice(), roomBookVo.getId());
    }

    @Override
    public List<RoomBookVo> findRoomBookByConsumer(Integer consumer) {
        Preconditions.checkNotNull(consumer, "入参consumer不能为空！");

        List<RoomBookPo> roomBookPos = roomBookRepository.findByConsumer(consumer);

        if (CollectionUtils.isEmpty(roomBookPos)) {
            log.info("通过客户id：{}没有查询到订单信息！", consumer);
            return Collections.emptyList();
        }

        List<Integer> roomIds = roomBookPos.stream()
                .map(RoomBookPo::getRoom)
                .collect(Collectors.toList());
        List<RoomVo> roomVos = roomRepository.findByIdIn(roomIds).stream()
                .map(roomPo -> {
                    RoomVo roomVo = new RoomVo();
                    BeanUtils.copyProperties(roomPo, roomVo);
                    return roomVo;
                })
                .collect(Collectors.toList());

        return roomBookPos.stream()
                .map(roomBookPo -> {
                    RoomBookVo roomBookVo = new RoomBookVo();
                    BeanUtils.copyProperties(roomBookPo, roomBookVo);
                    for (RoomVo roomVo : roomVos) {
                        if (roomVo.getId().equals(roomBookVo.getRoom())) {
                            roomBookVo.setRoomVo(roomVo);
                        }
                    }
                    return roomBookVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PrintInfoVo printBill(Integer consumer, Integer roomBook) {
        Preconditions.checkNotNull(consumer, "入参consumer不能为空！");
        Preconditions.checkNotNull(roomBook, "入参roomBook不能为空！");

        ConsumerPo consumerPo = consumerRepository.getById(consumer);
        RoomBookPo roomBookPo = roomBookRepository.getById(roomBook);
        RoomPo roomPo = roomRepository.getById(roomBookPo.getRoom());

        BigDecimal totalAmount = roomBookPo.getSettlementPrice()
                .add(roomBookPo.getDeposit())
                .subtract(roomBookPo.getSettlementPrice());
        BigDecimal daysFor = BigDecimal.valueOf(roomBookPo.getEndTime().getTime() - roomBookPo.getBeginTime().getTime())
                .divide(BigDecimal.valueOf(ONE_DAY_MILLISECONDS), SCALE, BigDecimal.ROUND_HALF_EVEN);

        return PrintInfoVo.builder()
                .consumerName(consumerPo.getName())
                .beginTime(roomBookPo.getBeginTime())
                .endTime(roomBookPo.getEndTime())
                .roomPrice(roomBookPo.getRoomPrice().multiply(daysFor))
                .roomNum(roomPo.getRoomNum())
                .days(daysFor)
                .totalAmount(totalAmount)
                .build();
    }

    /**
     * 转换
     * @param poPage
     * @return
     */
    private PageResult<RoomVo> convertPage(Page<RoomPo> poPage) {
        List<RoomPo> roomPos = poPage.getContent();

        List<RoomVo> roomVos = roomPos.stream()
                .map(roomPo -> {
                    RoomVo roomVo = new RoomVo();
                    BeanUtils.copyProperties(roomPo, roomVo);
                    return roomVo;
                })
                .collect(Collectors.toList());

        return PageResult.<RoomVo>builder()
                .curPage(poPage.getNumber() + 1)
                .size(poPage.getSize())
                .totalPages(poPage.getTotalPages())
                .totalElements(poPage.getTotalElements())
                .content(roomVos)
                .build();
    }

}
