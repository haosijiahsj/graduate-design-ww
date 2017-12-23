package com.zzz.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zzz.controller.model.MailForm;
import com.zzz.model.vo.MailVo;
import com.zzz.service.PushService;
import com.zzz.support.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
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

    @PostMapping("/sendSimpleEmailToConsumer")
    public ResponseEntity sendSimpleEmailToConsumer(MailForm mailForm) {
        try {
            MailVo mailVo = new MailVo();
            mailVo.setSubject(mailForm.getSubject());
            mailVo.setText(mailForm.getText());
            pushService.sendSimpleEmailToConsumer(mailForm.getIds(), mailVo);
            return ResponseEntity.successRequest();
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

    @PostMapping("/sendSimpleEmailToAllConsumer")
    public ResponseEntity sendSimpleEmailToAllConsumer(String subject, String text) {
        try {
            MailVo mailVo = MailVo.builder()
                    .subject(subject)
                    .text(text)
                    .build();
            pushService.sendSimpleEmailToAllConsumer(mailVo);
            return ResponseEntity.successRequest();
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

}
