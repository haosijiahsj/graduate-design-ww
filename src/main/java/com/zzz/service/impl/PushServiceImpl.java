package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.ConsumerRepository;
import com.zzz.model.po.ConsumerPo;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.MailVo;
import com.zzz.service.PushService;
import com.zzz.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by on 2017/12/22.
 */
@Slf4j
@Service
public class PushServiceImpl implements PushService {

    private static final String[] IGNORE_COPY = new String[] {"to"};
    private static final String EMAIL_SUFFIX = "@qq.com";

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public void sendSimpleEmail(MailVo mailVo) {
        Preconditions.checkNotNull(mailVo, "入参mailVo不能为空！");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(mailVo.getTo()), "入参to不能为空！");

        SimpleMailMessage message = new SimpleMailMessage();

        mailVo.setFrom(from);
        BeanUtils.copyProperties(mailVo, message, IGNORE_COPY);
        message.setTo(ConvertUtils.convertList2Array(mailVo.getTo(), String.class));

        mailSender.send(message);

        log.info("邮件发送成功！标题：{}，内容：{}", mailVo.getSubject(), mailVo.getText());
    }

    @Override
    public void sendSimpleEmailToConsumer(List<Integer> consumers, MailVo mailVo) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(consumers), "入参consumers不能为空！");

        List<ConsumerPo> consumerPos = consumerRepository.findByIdIn(consumers);

        if (CollectionUtils.isEmpty(consumerPos)) {
            log.info("通过批量id：{}，没有查询到客户信息！", consumers);
            return;
        }

        List<String> to = consumerPos.stream()
                .filter(consumerPo -> consumerPo.getTel() != null)
                .map(consumerPo -> consumerPo.getTel() + EMAIL_SUFFIX)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(to)) {
            mailVo.setTo(to);
            this.sendSimpleEmail(mailVo);
        }
    }

    @Override
    public void sendSimpleEmailToAllConsumer(MailVo mailVo) {
        Preconditions.checkNotNull(mailVo, "入参mailVo不能为空！");

        List<ConsumerPo> consumerPos = consumerRepository.findAll();

        List<String> to = consumerPos.stream()
                .filter(consumerPo -> consumerPo.getTel() != null)
                .map(consumerPo -> consumerPo.getTel() + EMAIL_SUFFIX)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(to)) {
            mailVo.setTo(to);
            this.sendSimpleEmail(mailVo);
        }
    }

}
