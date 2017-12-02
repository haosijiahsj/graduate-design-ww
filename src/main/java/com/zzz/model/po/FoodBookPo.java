package com.zzz.model.po;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "food_book")
public class FoodBookPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "consumer")
    private Integer consumer;

    @Column(name = "food")
    private Integer food;

    @Column(name = "num")
    private Integer num;

}
