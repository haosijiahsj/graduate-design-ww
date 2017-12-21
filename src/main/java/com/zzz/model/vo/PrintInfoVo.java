package com.zzz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 胡胜钧 on 12/20 0020.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrintInfoVo implements Serializable {

    private static final long serialVersionUID = 7268105724944077325L;

    private String consumerName;
    private String roomNum;
    private Date beginTime;
    private Date endTime;
    private BigDecimal days;
    private BigDecimal roomPrice;
    private BigDecimal totalAmount;

}
