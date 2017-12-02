package com.zzz.model.vo;

import com.zzz.enums.RoleType;
import lombok.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVo {

    private Integer id;
    private String username;
    private String password;
    private RoleType role;

}
