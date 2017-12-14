package com.zzz.task;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by on 2017/12/14.
 */
@Slf4j
@Component
public class MyTask {

    private static final long ONE_MINUTE = 60 * 1000;

//    @Scheduled(fixedRate = ONE_MINUTE)
//    public void printSomething() {
//        log.info("现在时间是：{}", new DateTime(new Date()).toString("yyyy-MM-dd HH:mm:ss"));
//    }

}
