package com.zzz.dao;

import com.zzz.model.po.ConsumerPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Modifying
    @Query("UPDATE ConsumerPo c SET c.idNum = ?2, c.name = ?3, c.sex = ?4, c.tel = ?5 WHERE c.id = ?1")
    void update(Integer id, String idNum, String name, Integer sex, String tel);

}
