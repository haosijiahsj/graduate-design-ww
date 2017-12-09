package com.zzz.service;

import com.zzz.model.vo.UserVo;

import java.util.List;

/**
 * Created by  on 2017/9/7.
 */
public interface UserService {

    List<UserVo> findAllUser();

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

    /**
     * 新建账号
     * @param userVos
     */
    void saveUser(List<UserVo> userVos);

    /**
     * 删除账号
     * @param ids
     */
    void deleteUser(List<Integer> ids);

}