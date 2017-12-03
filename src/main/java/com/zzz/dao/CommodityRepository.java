package com.zzz.dao;

import com.zzz.model.po.CommodityPo;
import org.springframework.data.repository.Repository;

/**
 * Created by 胡胜钧 on 12/3 0003.
 */
public interface CommodityRepository extends Repository<CommodityPo, Integer> {

    CommodityPo getById(Integer id);

}
