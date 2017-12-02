package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.ConsumerRepository;
import com.zzz.dao.RoomBookRepository;
import com.zzz.dao.RoomRepository;
import com.zzz.model.po.ConsumerPo;
import com.zzz.model.po.RoomBookPo;
import com.zzz.model.po.RoomPo;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.model.vo.RoomVo;
import com.zzz.service.RoomService;
import com.zzz.support.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        roomRepository.updateStatusById(false, roomBookPo.getRoom());
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
