package com.zzz.model.vo;

import lombok.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeVo {

    private Integer id;
    private String name;
    private Integer sex;
    private String tel;
    private String idNum;

}
