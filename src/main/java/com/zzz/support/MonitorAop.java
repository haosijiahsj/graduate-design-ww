package com.zzz.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author hushengjun
 * @date 2018/2/24
 */
@Slf4j
@Aspect
@Component
public class MonitorAop {

    @Pointcut("execution(* com.zzz.controller.*.*(..))")
    private void pointCutMethod() {}

    /**
     * 前置通知
     */
    @Before("pointCutMethod()")
    public void doBefore() {}

    /**
     * 访问目标方法返回值
     * @param result
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {}

    /**
     * 目标方法抛出异常通知
     * @param e
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {}

    /**
     * 后置通知
     */
    @After("pointCutMethod()")
    public void doAfter() {}

    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object object = proceedingJoinPoint.proceed();
        stopWatch.stop();

        log.info("{}请求执行完毕！请求参数：{}，请求耗时：{}ms",
                proceedingJoinPoint.getSignature().getName(),
                proceedingJoinPoint.getArgs(),
                stopWatch.getTime());

        return object;
    }

}
