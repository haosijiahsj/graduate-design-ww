package com.zzz.controller;

import com.google.common.collect.Maps;
import com.zzz.controller.model.UserForm;
import com.zzz.enums.RoleType;
import com.zzz.model.vo.UserVo;
import com.zzz.service.UserService;
import com.zzz.support.ResponseEntity;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by  on 2017/9/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity login(HttpSession session, String username, String password) {
        UserVo userVo = userService.findUserByUsernameAndPassword(username, password);
        ResponseEntity responseEntity = new ResponseEntity();

        if (userVo == null) {
            responseEntity.setMsgCode(400);
            responseEntity.setMsgContent("用户名或密码错误！");
            return responseEntity;
        }

        session.setAttribute("user", userVo);

        return ResponseEntity.successRequest();
    }

    /**
     * 获取系统所有角色
     * @return
     */
    @GetMapping("/roleType")
    public Map<String, String> roleType() {
        Map<String, String> map = Maps.newHashMap();
        Arrays.stream(RoleType.values())
                .forEach(roleType -> map.put(roleType.getCode(), roleType.getName()));
        return map;
    }

    /**
     * 添加新用户
     * @param userForms
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody List<UserForm> userForms) {
        List<UserVo> userVos = userForms.stream()
                .map(userForm -> {
                    UserVo userVo = new UserVo();
                    BeanUtils.copyProperties(userForm, userVo, "role");
                    userVo.setRole(RoleType.getByCode(userForm.getRole()));
                    return userVo;
                })
                .collect(Collectors.toList());
        userService.saveUser(userVos);
        return new ResponseEntity(ResponseStatus.SUCCESS);
    }

    /**
     * 批量删除用户
     * @param userForms
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody List<UserForm> userForms) {
        List<Integer> ids = userForms.stream()
                .map(UserForm::getId)
                .collect(Collectors.toList());
        userService.deleteUser(ids);
        return new ResponseEntity(ResponseStatus.SUCCESS);
    }

    @GetMapping("/findAllUser")
    public ResponseEntity findAllUser() {
        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(userService.findAllUser());
        return responseEntity;
    }

}
