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
import com.zzz.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by  on 12/2 0002.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomBookRepository roomBookRepository;

    @Override
    public ConsumerVo getConsumerByIdNum(String idNum) {
        Preconditions.checkNotNull(idNum, "入参idNum不能为空！");

        ConsumerPo consumerPo = consumerRepository.getByIdNum(idNum);

        if (consumerPo == null) {
            log.info("通过idNum:{}没有查询到客户信息！", idNum);
            return null;
        }

        List<RoomBookPo> roomBookPos = roomBookRepository.findByConsumerAndStatus(consumerPo.getId(), false);

        if (CollectionUtils.isEmpty(roomBookPos)) {
            log.info("通过consumer:{}没有没有查询到预定房间信息！", consumerPo.getId());
            return null;
        }

        List<Integer> roomIds = roomBookPos.stream()
                .map(RoomBookPo::getRoom)
                .collect(Collectors.toList());
        List<RoomPo> roomPos = roomRepository.findByIdIn(roomIds);
        // 转换为map
        Map<Integer, RoomVo> roomVoMap = roomPos.stream()
                .collect(Collectors.toMap(RoomPo::getId, roomPo -> {
                    RoomVo roomVo = new RoomVo();
                    BeanUtils.copyProperties(roomPo, roomVo);
                    return roomVo;
                }));

        ConsumerVo consumerVo = new ConsumerVo();
        BeanUtils.copyProperties(consumerPo, consumerVo);
        List<RoomBookVo> roomBookVos = roomBookPos.stream()
                .map(roomBookPo -> {
                    RoomBookVo roomBookVo = new RoomBookVo();
                    BeanUtils.copyProperties(roomBookPo, roomBookVo);
                    return roomBookVo;
                })
                .collect(Collectors.toList());
        // 设置vo中的vo
        List<RoomBookVo> roomBookVoList = roomBookVos.stream()
                .map(roomBookVo -> {
                    roomBookVo.setRoomVo(roomVoMap.get(roomBookVo.getRoom()));
                    return roomBookVo;
                })
                .collect(Collectors.toList());
        consumerVo.setRoomBookVos(roomBookVoList);

        return consumerVo;
    }
}
