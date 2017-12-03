package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zzz.dao.ConsumerRepository;
import com.zzz.dao.RoomBookRepository;
import com.zzz.dao.RoomRepository;
import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import com.zzz.model.po.ConsumerPo;
import com.zzz.model.po.RoomBookPo;
import com.zzz.model.po.RoomPo;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.model.vo.RoomVo;
import com.zzz.service.RoomService;
import com.zzz.support.PageResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private RoomBookRepository roomBookRepository;

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

        return this.convertPage(roomPoPage);
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
    public void settleRoom(List<RoomBookVo> roomBookVos) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(roomBookVos), "入参roomBookVos不能为空！");

        // 更新最终结算价格和房间状态
        roomBookVos.forEach(roomBookVo -> {
                roomBookRepository.updateSettlementPrice(roomBookVo.getSettlementPrice(), roomBookVo.getId());
                roomRepository.updateStatusById(RoomStatus.CAN_NOT_BOOK, roomBookVo.getRoom());
        });
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
