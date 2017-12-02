package com.zzz.controller;

import com.zzz.model.vo.UserVo;
import com.zzz.service.UserService;
import com.zzz.support.ResponseEntity;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hushengjun on 2017/9/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable("username") String username, @PathVariable("password") String password) {
        UserVo userVo = userService.findUserByUsernameAndPassword(username, password);
        ResponseEntity responseEntity = new ResponseEntity();

        if (userVo == null) {
            responseEntity.setMsgCode(400);
            responseEntity.setMsgContent("用户名或密码错误！");
            return responseEntity;
        }

        return new ResponseEntity(ResponseStatus.SUCCESS, userVo);
    }

}