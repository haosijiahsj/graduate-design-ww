package com.zzz.dao;

import com.zzz.model.po.ConsumerPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by  on 12/2 0002.
 */
public interface ConsumerRepository extends Repository<ConsumerPo, Integer>, JpaSpecificationExecutor<ConsumerPo> {

    void save(ConsumerPo consumerPo);

    ConsumerPo getById(Integer id);

    ConsumerPo getByIdNum(String idNum);

    List<ConsumerPo> findAll();

}
