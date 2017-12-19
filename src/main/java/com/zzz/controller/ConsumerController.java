package com.zzz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.service.ConsumerService;
import com.zzz.service.RoomService;
import com.zzz.support.ResponseEntity;
import com.zzz.support.ResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by on 12/17 0017.
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/findConsumer")
    public ResponseEntity findConsumer(String json) {
        if (StringUtils.isBlank(json)) {
            return ResponseEntity.failedRequestIllegalParam();
        }

        try {
            ConsumerVo consumerVo = JSONObject.parseObject(json, ConsumerVo.class);
            return ResponseEntity.successRequest(consumerService.findConsumers(consumerVo));
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

    @GetMapping("/findConsumerRoomBook")
    public ResponseEntity findConsumerRoomBook(Integer consumer) {
        if (consumer == null) {
            return ResponseEntity.failedRequestIllegalParam();
        }

        try {
            return ResponseEntity.successRequest(roomService.findRoomBookByConsumer(consumer));
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

}
