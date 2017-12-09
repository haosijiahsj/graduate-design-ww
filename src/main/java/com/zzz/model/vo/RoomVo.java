package com.zzz.model.vo;

import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by  on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomVo implements Serializable {

    private static final long serialVersionUID = 3325799888151101252L;

    private Integer id;
    private String roomNum;
    private BigDecimal price;
    private RoomStatus status;
    private RoomType type;

}
