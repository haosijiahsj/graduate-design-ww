package com.zzz.model.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by  on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomBookVo implements Serializable {

    private static final long serialVersionUID = -2815883455784047483L;

    private Integer id;
    private Integer consumer;
    private Integer room;
    private BigDecimal deposit;
    private BigDecimal roomPrice;
    private Date beginTime;
    private Date endTime;
    private BigDecimal settlementPrice;
    private Boolean status;
    private RoomVo roomVo;

}
