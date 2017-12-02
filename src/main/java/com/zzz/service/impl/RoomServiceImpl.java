package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.RoomRepository;
import com.zzz.model.po.RoomPo;
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

    @Override
    public PageResult<RoomVo> findAllRoom(Pageable pageable) {
        Preconditions.checkNotNull(pageable, "入参pageable不能为空！");

        Page<RoomPo> roomPoPage = roomRepository.findAll(pageable);

        return this.convertPage(roomPoPage);
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
                .totalPages(poPage.getTotalPages())
                .totalElements(poPage.getTotalElements())
                .content(roomVos)
                .build();
    }

}
