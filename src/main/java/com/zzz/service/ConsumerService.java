package com.zzz.service;

import com.zzz.model.vo.ConsumerVo;

import java.util.List;

/**
 * Created by on 12/2 0002.
 */
public interface ConsumerService {

    /**
     * 通过用户身份证号查询富vo信息
     * @param idNum
     * @return
     */
    ConsumerVo getConsumerByIdNum(String idNum);

    /**
     * 查询所有的客户，包含富vo信息
     * @return
     */
    List<ConsumerVo> findAllNoSettle();

    /**
     * 动态查询
     * @param consumerVo
     * @return
     */
    List<ConsumerVo> findConsumers(ConsumerVo consumerVo);

}
