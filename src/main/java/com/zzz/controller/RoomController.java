package com.zzz.controller;

import com.zzz.enums.RoomType;
import com.zzz.service.RoomService;
import com.zzz.support.ResponseEntity;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/roomType")
    public List<String> getRoomType() {
        return Arrays.stream(RoomType.values())
                .map(RoomType::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/findAllRoom")
    public ResponseEntity findAllRoom(Integer page, Integer size) {
        page = page == null ? 1 : page;
        size = size == null ? 20 : size;
        Pageable pageable = new PageRequest(page - 1, size);

        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(roomService.findAllRoom(pageable));

        return responseEntity;
    }

}
