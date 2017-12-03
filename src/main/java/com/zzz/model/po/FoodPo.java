package com.zzz.model.po;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "food")
public class FoodPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "price")
    private BigDecimal price;

}
