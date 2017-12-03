package com.zzz.controller;

import com.zzz.controller.model.BookRoomForm;
import com.zzz.enums.RoomStatus;
import com.zzz.enums.RoomType;
import com.zzz.model.vo.CommodityBookVo;
import com.zzz.model.vo.ConsumerVo;
import com.zzz.model.vo.RoomBookVo;
import com.zzz.service.CommodityService;
import com.zzz.service.ConsumerService;
import com.zzz.service.RoomService;
import com.zzz.support.ResponseEntity;
import com.zzz.support.ResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/roomType")
    public List<String> getRoomType() {
        return Arrays.stream(RoomType.values())
                .map(RoomType::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/findAllCanBookRoom")
    public ResponseEntity findAllCanBookRoom(Integer page, Integer size) {
        page = page == null ? 1 : page;
        size = size == null ? 20 : size;
        Pageable pageable = new PageRequest(page - 1, size);

        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(roomService.findAllRoomByStatus(RoomStatus.CAN_BOOK, pageable));

        return responseEntity;
    }

    @PostMapping("/bookRoom")
    public ResponseEntity bookRoom(@RequestBody BookRoomForm bookRoomForm) {
        if (bookRoomForm == null) {
            return ResponseEntity.builder()
                    .msgCode(400)
                    .msgContent("必须输入顾客信息和预定房间号")
                    .build();
        }

        RoomBookVo roomBookVo = new RoomBookVo();
        roomBookVo.setRoom(bookRoomForm.getRoom());
        roomBookVo.setDeposit(bookRoomForm.getDeposit());
        roomBookVo.setRoomPrice(bookRoomForm.getRoomPrice());
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        roomBookVo.setBeginTime(DateTime.parse(bookRoomForm.getBeginTime(), formatter).toDate());
        roomBookVo.setEndTime(DateTime.parse(bookRoomForm.getEndTime(), formatter).toDate());

        ConsumerVo consumerVo = ConsumerVo.builder()
                .idNum(bookRoomForm.getIdNum())
                .name(bookRoomForm.getName())
                .sex(bookRoomForm.getSex())
                .tel(bookRoomForm.getTel())
                .build();

        roomService.bookRoom(roomBookVo, consumerVo);

        return new ResponseEntity(ResponseStatus.SUCCESS);
    }

    @GetMapping("/getConsumerRoomBook")
    public ResponseEntity getConsumerRoomBook(String idNum) {
        if (StringUtils.isBlank(idNum)) {
            return ResponseEntity.builder().msgCode(400).msgContent("身份证号不能为空！").build();
        }

        ConsumerVo consumerVo = consumerService.getConsumerByIdNum(idNum);
        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(consumerVo);

        return responseEntity;
    }

    @PostMapping("/saveCommodityBook")
    public ResponseEntity saveCommodityBook(List<CommodityBookVo> commodityBookVos) {
        commodityService.saveCommodityBook(commodityBookVos);

        return new ResponseEntity(ResponseStatus.SUCCESS);
    }

    @PostMapping("/settleRoom")
    public ResponseEntity settleRoom(RoomBookVo roomBookVo) {
        RoomBookVo returnRoomBookVo = roomService.settleRoom(roomBookVo);
        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(returnRoomBookVo);
        return responseEntity;
    }

    @PostMapping("/updateRoomStatus")
    public ResponseEntity updateRoomStatus(RoomBookVo roomBookVo) {
        roomService.updateRoomForSettle(roomBookVo);
        return new ResponseEntity(ResponseStatus.SUCCESS);
    }

}
