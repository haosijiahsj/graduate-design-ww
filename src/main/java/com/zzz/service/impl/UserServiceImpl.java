package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.UserRepository;
import com.zzz.model.po.UserPo;
import com.zzz.model.vo.UserVo;
import com.zzz.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ok
 * Created by hushengjun on 2017/9/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVo findUserByUsernameAndPassword(String username, String password) {
        Preconditions.checkNotNull(username, "入参name不能为空！");
        Preconditions.checkNotNull(password, "入参password不能为空！");

        UserPo userPo = userRepository.findUserByUsernameAndPassword(username, password);
        UserVo userVo = new UserVo();
        if (userPo == null) {
            return null;
        }
        BeanUtils.copyProperties(userPo, userVo);

        return userVo;
    }

    @Override
    public void updateUser(UserVo userVo) {
        Preconditions.checkNotNull(userVo, "入参userVo不能为空！");

        userRepository.update(userVo.getUsername(), userVo.getPassword(), userVo.getRole(), userVo.getId());
    }

}
