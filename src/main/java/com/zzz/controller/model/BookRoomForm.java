package com.zzz.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by  on 12/2 0002.
 */
@Data
public class BookRoomForm {

    private Integer room;
    private BigDecimal deposit;
    private BigDecimal roomPrice;
    private String beginTime;
    private String endTime;
    private String idNum;
    private String name;
    private Integer sex;
    private String tel;

}
