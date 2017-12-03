package com.zzz.model.vo;

import lombok.*;

import java.util.List;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumerVo {

    private Integer id;
    private String idNum;
    private String name;
    private Integer sex;
    private String tel;
    private List<RoomBookVo> roomBookVos;
}
