package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.model.vo.MailVo;
import com.zzz.service.PushService;
import com.zzz.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by on 2017/12/22.
 */
@Slf4j
@Service
public class PushServiceImpl implements PushService {

    private static final String[] IGNORE_COPY = new String[] {"to"};

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleEmail(MailVo mailVo) {
        Preconditions.checkNotNull(mailVo, "入参mailVo不能为空！");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(mailVo.getTo()), "入参to不能为空！");

        SimpleMailMessage message = new SimpleMailMessage();

        BeanUtils.copyProperties(mailVo, message, IGNORE_COPY);
        message.setTo(ConvertUtils.convertList2Array(mailVo.getTo(), String.class));

        mailSender.send(message);

        log.info("邮件发送成功！标题：{}，内容：{}", mailVo.getSubject(), mailVo.getText());
    }

}
