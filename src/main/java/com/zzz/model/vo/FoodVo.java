package com.zzz.model.vo;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodVo {

    private Integer id;
    private String foodName;
    private BigDecimal price;

}
