package com.zzz.model.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomBookVo {

    private Integer id;
    private Integer consumer;
    private Integer room;
    private Date beginTime;
    private Date endTime;
    private BigDecimal settlementPrice;

}
