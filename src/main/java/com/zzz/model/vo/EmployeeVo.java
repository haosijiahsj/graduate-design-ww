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
public class EmployeeVo implements Serializable {

    private static final long serialVersionUID = -3575008682062414766L;

    private Integer id;
    private String name;
    private Integer sex;
    private String tel;
    private String idNum;

}
