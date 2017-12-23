package com.zzz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.service.CommodityService;
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

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/findConsumer")
    public ResponseEntity findConsumer(String json) {
        try {
            ConsumerVo consumerVo = null;
            if (StringUtils.isNotEmpty(json)) {
                consumerVo = JSONObject.parseObject(json, ConsumerVo.class);
            }
            return ResponseEntity.successRequest(consumerService.findConsumers(consumerVo));
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

    @GetMapping("/findConsumerRoomBook")
    public ResponseEntity findConsumerRoomBook(Integer id) {
        if (id == null) {
            return ResponseEntity.failedRequestIllegalParam();
        }
        try {
            return ResponseEntity.successRequest(roomService.findRoomBookByConsumer(id));
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

    @GetMapping("/findByRoomBook")
    public ResponseEntity findByRoomBook(Integer id) {
        if (id == null) {
            return ResponseEntity.failedRequestIllegalParam();
        }
        try {
            return ResponseEntity.successRequest(commodityService.findByRoomBook(id));
        } catch (Exception e) {
            return ResponseEntity.failedRequest(e.getMessage());
        }
    }

}
