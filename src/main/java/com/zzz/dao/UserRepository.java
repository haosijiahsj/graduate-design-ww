package com.zzz.dao;

import com.zzz.enums.RoleType;
import com.zzz.model.po.UserPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * userDao
 * Created by hushengjun on 2017/9/14.
 */
public interface UserRepository extends Repository<UserPo, Integer> {
	
    List<UserPo> findAll();

    UserPo findById(Long id);

    UserPo findUserByUsernameAndPassword(String username, String password);

    void save(UserPo user);

    @Modifying
    @Query("UPDATE UserPo u SET u.username = ?1, password = ?2, u.role = ?3 WHERE u.id = ?4")
    void update(String username, String password, RoleType role, Integer id);

}
