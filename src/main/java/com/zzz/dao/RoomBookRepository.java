package com.zzz.dao;

import com.zzz.model.po.RoomBookPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface RoomBookRepository extends Repository<RoomBookPo, Integer> {

    void save(RoomBookPo roomBookPo);

    List<RoomBookPo> findByConsumer(Integer consumer);

    @Modifying
    @Query("UPDATE RoomBookPo rb SET rb.settlementPrice = ?1 WHERE rb.id = ?2")
    void updateSettlementPrice(BigDecimal settlementPrice, Integer id);

}
