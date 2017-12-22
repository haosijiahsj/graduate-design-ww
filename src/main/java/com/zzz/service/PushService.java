package com.zzz.service;

import com.zzz.model.vo.MailVo;

/**
 * Created by on 2017/12/22.
 */
public interface PushService {

    void sendSimpleEmail(MailVo mailVo);

}
