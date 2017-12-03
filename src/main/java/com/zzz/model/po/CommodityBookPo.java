package com.zzz.model.po;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "commodity_book")
public class CommodityBookPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "room_book")
    private Integer roomBook;

    @Column(name = "commodity")
    private Integer commodity;

    @Column(name = "num")
    private Integer num;

}
