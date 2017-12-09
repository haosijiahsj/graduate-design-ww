package com.zzz.model.vo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by  on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumerVo implements Serializable {

    private static final long serialVersionUID = -5989989186992102895L;

    private Integer id;
    private String idNum;
    private String name;
    private Integer sex;
    private String tel;
    private List<RoomBookVo> roomBookVos;
}
