package com.zzz.model.vo;

import lombok.*;

import java.io.Serializable;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommodityBookVo implements Serializable {

    private static final long serialVersionUID = 2054322122900896457L;

    private Integer id;
    private Integer roomBook;
    private Integer commodity;
    private Integer num;
    private CommodityVo commodityVo;

}
