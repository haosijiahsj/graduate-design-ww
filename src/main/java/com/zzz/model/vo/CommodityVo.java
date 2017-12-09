package com.zzz.model.vo;

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
public class CommodityVo implements Serializable {

    private static final long serialVersionUID = 2377543401255615592L;

    private Integer id;
    private String commodityName;
    private BigDecimal price;

}
