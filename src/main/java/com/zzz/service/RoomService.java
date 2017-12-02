package com.zzz.service;

import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.model.vo.RoomVo;
import com.zzz.support.PageResult;
import org.springframework.data.domain.Pageable;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface RoomService {

    PageResult<RoomVo> findAllRoom(Pageable pageable);

    void bookRoom(RoomBookVo roomBookVo, ConsumerVo consumerVo);

}
