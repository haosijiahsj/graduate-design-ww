package com.zzz.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private BigDecimal days;
    private BigDecimal roomPrice;
    private BigDecimal totalAmount;

}
