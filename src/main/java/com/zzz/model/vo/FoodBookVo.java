package com.zzz.model.vo;

import lombok.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodBookVo {

    private Integer id;
    private Integer consumer;
    private Integer food;
    private Integer num;

}
