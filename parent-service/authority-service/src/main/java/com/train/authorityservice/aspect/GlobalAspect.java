package com.train.authorityservice.aspect;


import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 全局切面类
 *
 * @author zhangLei
 * @serial 2019/09/23
 */
@Aspect
@Component
public class GlobalAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalAspect.class);

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation syslog = method.getAnnotation(ApiOperation.class);
        if (syslog != null) {
            LOGGER.info(syslog.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        LOGGER.info(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            for (int x = 0; x < args.length; x++) {
                LOGGER.info("参数[" + x + "]===>" + args[x]);
            }
        }
    }
}
