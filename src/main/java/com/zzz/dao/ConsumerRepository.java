package com.zzz.dao;

import com.zzz.model.po.ConsumerPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * Created by  on 12/2 0002.
 */
public interface ConsumerRepository extends Repository<ConsumerPo, Integer>, JpaSpecificationExecutor<ConsumerPo> {

    void save(ConsumerPo consumerPo);

    ConsumerPo getByIdNum(String idNum);

}
