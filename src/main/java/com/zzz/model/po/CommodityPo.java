package com.zzz.model.po;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "commodity")
public class CommodityPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "price")
    private BigDecimal price;

}
