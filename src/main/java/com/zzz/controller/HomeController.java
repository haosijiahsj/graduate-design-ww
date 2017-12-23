package com.zzz.controller;

import com.zzz.controller.model.CountRoomView;
import com.zzz.model.vo.UserVo;
import com.zzz.service.RoomService;
import com.zzz.support.*;
import com.zzz.support.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//        if (userVo == null) {
//            return new ResponseEntity(ResponseStatus.FAILED_LOGIN);
//        }

        Map<String, Integer> map = roomService.countCanBookRoomNumByType();
        List<CountRoomView> countRoomViews = map.entrySet().stream()
                .map(m -> {
                    CountRoomView countRoomView = new CountRoomView();
                    countRoomView.setName(m.getKey());
                    countRoomView.setNum(map.get(m.getKey()));
                    return countRoomView;
                })
                .collect(Collectors.toList());

        return ResponseEntity.successRequest(countRoomViews);
    }

}
