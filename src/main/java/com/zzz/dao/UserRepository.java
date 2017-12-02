package com.zzz.dao;

import com.zzz.model.po.UserPo;
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

}
