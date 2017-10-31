package com.zzz.service;

import com.zzz.domain.User;
import com.zzz.domain.UserInfo;

import java.util.List;

/**
 * Created by hushengjun on 2017/9/7.
 */
public interface UserService {

    List<User> findAllUser();

    User findUserById(Long id);

    List<UserInfo> findAllMaleUserInfo();

    List<Long> findAllUserId();

}