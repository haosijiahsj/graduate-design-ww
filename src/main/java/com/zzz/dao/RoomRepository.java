package com.zzz.dao;

import com.zzz.model.po.RoomPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface RoomRepository extends Repository<RoomPo, Integer> {

    Page<RoomPo> findAll(Pageable pageable);

    @Query("UPDATE RoomPo r SET r.status = ?1 WHERE r.id = ?2")
    void updateStatusById(Boolean status, Integer id);

}
