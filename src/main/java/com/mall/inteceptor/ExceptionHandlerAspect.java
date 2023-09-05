//package com.mall.inteceptor;
//
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//
//
//@Aspect
//@Component
//public class ExceptionHandlerAspect {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @AfterThrowing(pointcut="execution(* com.sample.service..*(..))", throwing="ex")
//    public void handleServiceException(Exception ex) {
//        logger.error("Service exception: " + ex);
//    }
//
//    @AfterThrowing(pointcut="execution(* com.sample.controller..*(..))", throwing="ex")
//    public void handleControllerException(Exception ex) {
//        logger.error("Controller exception: " + ex);
//    }
//
//}