package com.zzz.service;

import com.zzz.model.vo.ConsumerVo;

/**
 * Created by  on 12/2 0002.
 */
public interface ConsumerService {

    /**
     * 通过用户身份证号查询富vo信息
     * @param idNum
     * @return
     */
    ConsumerVo getConsumerByIdNum(String idNum);

}
