package com.zzz.service;

import com.zzz.model.vo.UserVo;

/**
 * Created by hushengjun on 2017/9/7.
 */
public interface UserService {

    UserVo findUserByUsernameAndPassword(String username, String password);

}