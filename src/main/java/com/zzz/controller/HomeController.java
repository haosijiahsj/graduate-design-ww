package com.zzz.controller;

import com.zzz.service.RoomService;
import com.zzz.support.*;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by  on 2017/9/14.
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/countCanBookRoom")
    public ResponseEntity countCanBookRoom() {
        ResponseEntity responseEntity = new ResponseEntity(ResponseStatus.SUCCESS);
        responseEntity.setResult(roomService.countCanBookRoomNumByType());

        return responseEntity;
    }

}
