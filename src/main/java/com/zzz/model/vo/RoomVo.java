package com.zzz.model.vo;

import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomVo {

    private Integer id;
    private String roomNum;
    private BigDecimal price;
    private RoomStatus status;
    private RoomType type;

}
