package com.zzz.dao;

import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import com.zzz.model.po.RoomPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by  on 12/2 0002.
 */
public interface RoomRepository extends Repository<RoomPo, Integer> {

    Page<RoomPo> findAll(Pageable pageable);

    Page<RoomPo> findByStatus(RoomStatus status, Pageable pageable);

    List<RoomPo> findByStatus(RoomStatus status);

    List<RoomPo> findByIdIn(List<Integer> ids);

    @Modifying
    @Query("UPDATE RoomPo r SET r.status = ?1 WHERE r.id = ?2")
    void updateStatusById(RoomStatus status, Integer id);

    @Modifying
    @Query("UPDATE RoomPo r SET r.price = ?1, r.status = ?2, r.type = ?3 WHERE r.id = ?4")
    void update(BigDecimal price, RoomStatus status, RoomType type, Integer id);

}
