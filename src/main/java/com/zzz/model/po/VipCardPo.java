package com.zzz.model.po;

import com.zzz.enums.VipCardLevel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by on 2017/12/13.
 */
@Data
@Entity
@Table(name = "vip_card")
public class VipCardPo {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private VipCardLevel level;

}
