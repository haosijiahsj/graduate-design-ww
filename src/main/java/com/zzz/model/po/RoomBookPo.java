package com.zzz.model.po;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "room_book")
public class RoomBookPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "consumer")
    private Integer consumer;

    @Column(name = "room")
    private Integer room;

    @Column(name = "deposit")
    private BigDecimal deposit;

    @Column(name = "room_price")
    private BigDecimal roomPrice;

    @Column(name = "begin_time")
    private Date beginTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "settlement_price")
    private BigDecimal settlementPrice;

    @Column(name = "status_")
    private Boolean status;

}
