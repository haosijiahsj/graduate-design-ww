package com.zzz.service;

import com.zzz.model.vo.UserVo;

/**
 * Created by hushengjun on 2017/9/7.
 */
public interface UserService {

    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    UserVo findUserByUsernameAndPassword(String username, String password);

    /**
     * 更新用户的密码
     * @param userVo
     */
    void updateUser(UserVo userVo);

}