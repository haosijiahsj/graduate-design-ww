package com.zzz.dao;

import com.zzz.model.po.CommodityBookPo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by  on 12/3 0003.
 */
public interface CommodityBookRepository extends Repository<CommodityBookPo, Integer> {

    void save(CommodityBookPo commodityBookPo);

    List<CommodityBookPo> findByRoomBook(Integer roomBook);

}
