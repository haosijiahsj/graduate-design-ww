package com.zzz.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private BigDecimal settlementPrice;
    private Boolean status;
    private RoomVo roomVo;

}
