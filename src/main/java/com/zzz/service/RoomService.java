package com.zzz.service;

import com.zzz.enums.RoomStatus;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.model.vo.RoomVo;
import com.zzz.support.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface RoomService {

    /**
     * 查询所有房间
     * @param pageable
     * @return
     */
    PageResult<RoomVo> findAllRoom(Pageable pageable);

    /**
     * 通过状态查询房间
     * @param status
     * @param pageable
     * @return
     */
    PageResult<RoomVo> findAllRoomByStatus(RoomStatus status, Pageable pageable);

    /**
     * 查询各个类型房间可预订的数量，dashboard使用
     * @return
     */
    Map<String, Integer> countCanBookRoomNumByType();

    /**
     * 预定房间
     * @param roomBookVo
     * @param consumerVo
     */
    void bookRoom(RoomBookVo roomBookVo, ConsumerVo consumerVo);

    /**
     * 更新房间信息
     * @param roomVo
     */
    void updateRoom(RoomVo roomVo);

    /**
     * 结算
     * @param roomBookVos
     */
    void settleRoom(List<RoomBookVo> roomBookVos);

}
