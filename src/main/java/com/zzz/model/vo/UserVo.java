package com.zzz.model.vo;

import com.zzz.enums.RoleType;
import lombok.*;

import java.io.Serializable;

/**
 * Created by  on 12/2 0002.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = -8101898660361068647L;

    private Integer id;
    private String username;
    private String password;
    private RoleType role;

}
