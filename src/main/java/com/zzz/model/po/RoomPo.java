package com.zzz.model.po;

import com.zzz.enums.RoomType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "room")
public class RoomPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "room_num")
    private String roomNum;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status_")
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoomType type;

}
