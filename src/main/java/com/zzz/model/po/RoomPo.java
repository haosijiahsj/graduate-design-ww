package com.zzz.model.po;

import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by  on 12/2 0002.
 */
@Data
@Entity
@Table(name = "room")
public class RoomPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "room_num")
    private String roomNum;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_")
    private RoomStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoomType type;

}
