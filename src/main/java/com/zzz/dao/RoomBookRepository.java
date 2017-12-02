package com.zzz.dao;

import com.zzz.model.po.RoomBookPo;
import org.springframework.data.repository.Repository;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface RoomBookRepository extends Repository<RoomBookPo, Integer> {

    void save(RoomBookPo roomBookPo);

}
