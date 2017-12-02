package com.zzz.dao;

import com.zzz.model.po.ConsumerPo;
import org.springframework.data.repository.Repository;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface ConsumerRepository extends Repository<ConsumerPo, Integer> {

    void save(ConsumerPo consumerPo);

}
