package com.zzz.model.po;

import com.zzz.enums.RoleType;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by  on 12/2 0002.
 */
@Data
@Entity
@Table(name = "user")
public class UserPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

}
