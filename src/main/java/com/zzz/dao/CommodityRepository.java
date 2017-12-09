package com.zzz.dao;

import com.zzz.model.po.CommodityPo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Created by  on 12/3 0003.
 */
public interface CommodityRepository extends Repository<CommodityPo, Integer> {

    CommodityPo getById(Integer id);
    
    List<CommodityPo> findAll();
    
    void save(CommodityPo commodityPo);
    
    @Modifying
    @Query(value="update CommodityPo c set c.commodityName = ?2 , c.price = ?3 where c.id = ?1")
    void update(Integer id, String commodityName, BigDecimal price);
}
