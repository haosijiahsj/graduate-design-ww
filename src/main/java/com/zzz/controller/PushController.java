package com.zzz.controller;

import com.google.common.collect.Lists;
import com.zzz.model.vo.MailVo;
import com.zzz.service.PushService;
import com.zzz.support.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by on 2017/12/22.
 */
@RequestMapping("/push")
@RestController
public class PushController {

    @Autowired
    private PushService pushService;

    @PostMapping("/sendSimpleEmail")
    public ResponseEntity sendSimpleEmail() {
        MailVo mailVo = MailVo.builder()
                .from("1017547773@qq.com")
                .to(Lists.newArrayList("18408243831@qq.com"))
                .sentDate(new Date())
                .subject("测试发送邮件")
                .text("发送的内容")
                .build();

        try {
            pushService.sendSimpleEmail(mailVo);
            return ResponseEntity.successRequest();
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

}
