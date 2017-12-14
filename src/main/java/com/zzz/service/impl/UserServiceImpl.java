package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.UserRepository;
import com.zzz.model.po.UserPo;
import com.zzz.model.vo.UserVo;
import com.zzz.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by  on 2017/9/14.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserVo> findAllUser() {
        return userRepository.findAll().stream()
                .map(userPo -> {
                    UserVo userVo = new UserVo();
                    BeanUtils.copyProperties(userPo, userVo);
                    return userVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserVo findUserByUsernameAndPassword(String username, String password) {
        Preconditions.checkNotNull(username, "入参name不能为空！");
        Preconditions.checkNotNull(password, "入参password不能为空！");

        UserPo userPo = userRepository.findUserByUsernameAndPassword(username, password);
        if (userPo == null) {
            return null;
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userPo, userVo);

        return userVo;
    }

    @Override
    public void updateUser(UserVo userVo) {
        Preconditions.checkNotNull(userVo, "入参userVo不能为空！");

        userRepository.update(userVo.getUsername(), userVo.getPassword(), userVo.getRole(), userVo.getId());
    }

    @Override
    public void saveUser(List<UserVo> userVos) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(userVos), "入参userVos不能为空！");

        userVos.forEach(userVo -> {
                    UserPo userPo = new UserPo();
                    BeanUtils.copyProperties(userVo, userPo);
                    userRepository.save(userPo);
                });
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        Preconditions.checkNotNull(CollectionUtils.isNotEmpty(ids), "入参ids不能为空！");

        userRepository.deleteByIdIn(ids);
    }

}
