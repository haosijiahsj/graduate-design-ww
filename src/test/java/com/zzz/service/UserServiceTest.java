package com.zzz.service;

import com.zzz.BaseTest;
import com.zzz.model.vo.UserVo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserService单元测试
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAllUser() {
        List<UserVo> userVos = userService.findAllUser();

        // 断言查询不为空
        Assert.assertTrue(CollectionUtils.isNotEmpty(userVos));
    }

}
