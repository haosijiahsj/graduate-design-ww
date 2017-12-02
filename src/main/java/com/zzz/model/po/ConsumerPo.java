package com.zzz.model.po;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@Data
@Entity
@Table(name = "consumer")
public class ConsumerPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_num")
    private String idNum;

    @Column(name = "name_")
    private String name;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "tel")
    private String tel;

}
