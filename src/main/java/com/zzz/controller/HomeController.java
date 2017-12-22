package com.zzz.controller;

import com.zzz.model.vo.UserVo;
import com.zzz.service.RoomService;
import com.zzz.support.*;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by  on 2017/9/14.
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/countCanBookRoom")
    public ResponseEntity countCanBookRoom(HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute("user");
        if (userVo == null) {
            return new ResponseEntity(ResponseStatus.FAILED_LOGIN);
        }

        return ResponseEntity.successRequest(roomService.countCanBookRoomNumByType());
    }

}
