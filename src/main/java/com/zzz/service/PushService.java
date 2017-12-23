package com.zzz.service;

import com.zzz.model.vo.MailVo;

import java.util.List;

/**
 * Created by on 2017/12/22.
 */
public interface PushService {

    void sendSimpleEmail(MailVo mailVo);

    void sendSimpleEmailToConsumer(List<Integer> consumers, MailVo mailVo);

    void sendSimpleEmailToAllConsumer(MailVo mailVo);

}
