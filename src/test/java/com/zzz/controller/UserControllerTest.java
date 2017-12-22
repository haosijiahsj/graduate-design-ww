package com.zzz.controller;

import com.zzz.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * UserController单元测试
 * 使用MockMvc测试restful接口
 */
@Slf4j
public class UserControllerTest extends BaseTest {

    private static final String BASE_URI = "/user";

    @Test
    public void findAllUserTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URI + "/findAllUser");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue(status == 200);
        Assert.assertTrue(StringUtils.isNotEmpty(result));

        log.info("返回结果：{}", result);
    }

}
