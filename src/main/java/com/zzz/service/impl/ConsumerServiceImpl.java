package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
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
import com.zzz.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
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

    @Override
    public List<ConsumerVo> findAllNoSettle() {
        List<ConsumerPo> consumerPos = consumerRepository.findAll();
        List<ConsumerVo> consumerVos = ConvertUtils.convertPos2Vos(consumerPos, ConsumerVo.class);

        List<ConsumerVo> finalConsumerVos = Lists.newArrayList();
        for (ConsumerVo consumerVo : consumerVos) {
            List<RoomBookPo> roomBookPos = roomBookRepository.findByConsumerAndStatus(consumerVo.getId(), false);
            if (CollectionUtils.isNotEmpty(roomBookPos)) {
                List<RoomBookVo> roomBookVos = ConvertUtils.convertPos2Vos(roomBookPos, RoomBookVo.class);
                for (RoomBookVo roomBookVo : roomBookVos) {
                    RoomPo roomPo = roomRepository.getById(roomBookVo.getRoom());
                    RoomVo roomVo = new RoomVo();
                    BeanUtils.copyProperties(roomPo, roomVo);
                    roomBookVo.setRoomVo(roomVo);
                }
                consumerVo.setRoomBookVos(roomBookVos);
                finalConsumerVos.add(consumerVo);
            }
        }
        return finalConsumerVos;
    }

    @Override
    public List<ConsumerVo> findConsumers(ConsumerVo consumerVo) {
        if (consumerVo == null) {
            return ConvertUtils.convertPos2Vos(consumerRepository.findAll(), ConsumerVo.class);
        }

        Specification<ConsumerPo> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (consumerVo.getIdNum() != null) {
                predicates.add(criteriaBuilder.like(root.get("idNum"), "%" + consumerVo.getIdNum() + "%"));
            }
            if (consumerVo.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + consumerVo.getName() + "%"));
            }
            if (consumerVo.getSex() != null) {
                predicates.add(criteriaBuilder.equal(root.get("sex"), consumerVo.getSex()));
            }
            if (consumerVo.getTel() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tel"), consumerVo.getTel()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        return consumerRepository.findAll(specification).stream()
                .map(consumerPo -> {
                    ConsumerVo consumer = new ConsumerVo();
                    BeanUtils.copyProperties(consumerPo, consumer);
                    return consumer;
                })
                .collect(Collectors.toList());
    }

}
