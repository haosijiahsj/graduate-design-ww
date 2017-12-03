package com.zzz.model.vo;

import lombok.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommodityBookVo {

    private Integer id;
    private Integer roomBook;
    private Integer commodity;
    private Integer num;
    private CommodityVo commodityVo;

}
