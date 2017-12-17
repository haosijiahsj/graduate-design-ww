package com.zzz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.service.ConsumerService;
import com.zzz.service.RoomService;
import com.zzz.support.ResponseEntity;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ConsumerVo consumerVo = JSONObject.parseObject(json, ConsumerVo.class);
        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(consumerService.findConsumers(consumerVo));
        return responseEntity;
    }

    @GetMapping("/findConsumerRoomBook")
    public ResponseEntity findConsumerRoomBook(Integer consumer) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(roomService.findRoomBookByConsumer(consumer));
        return responseEntity;
    }

}
