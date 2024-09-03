package kms.bootcamp.footballturfmanagementservice.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
@Log4j2
public class TestServiceAspect {

    @Before(value = "execution(* kms.bootcamp.footballturfmanagementservice.service.impl.*.*(..))")
    public void before(JoinPoint joinpoint) {
        log.debug("Start " + joinpoint.getSignature().toShortString());
    }

//    @Around(value = "within(kms.bootcamp.footballturfmanagementservice.controller..*) ")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        joinPoint.proceed();
//        long elapsedTime = System.currentTimeMillis() - start;
//        log.info("Method '" + joinPoint.getSignature().toShortString() + "' execution time = [" + elapsedTime + "] ms.");
//    }

//    @After(value = "execution(* kms.bootcamp.footballturfmanagementservice.service.impl.*.*(..))")
//    public void after(JoinPoint joinpoint) {
//        log.info("after call " + joinpoint.getSignature().toShortString());
//    }

//    @AfterReturning(value = "execution(* kms.bootcamp.footballturfmanagementservice.service.impl.*.*(..))")
//    public void afterReturning(JoinPoint joinpoint) {
//        log.info("After Returning " + joinpoint.toString());
//    }
//
//    @AfterThrowing(value = "execution(* kms.bootcamp.footballturfmanagementservice.service.impl.*.*(..))")
//    public void afterThrowing(JoinPoint joinpoint) {
//        log.info("After Throwing " + joinpoint.toString());
//    }

}
