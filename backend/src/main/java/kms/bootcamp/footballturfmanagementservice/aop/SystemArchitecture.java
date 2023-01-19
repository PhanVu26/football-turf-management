package kms.bootcamp.footballturfmanagementservice.aop;

import kms.bootcamp.footballturfmanagementservice.constant.StatusCodeConstant;
import kms.bootcamp.footballturfmanagementservice.dto.Header;
import kms.bootcamp.footballturfmanagementservice.dto.HttpBase;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Log4j2
@Component
public class SystemArchitecture {

    @Pointcut("within(kms.bootcamp.footballturfmanagementservice.controller..*)")
    public void inControllerLayer() {}

    @Pointcut("within(kms.bootcamp.footballturfmanagementservice.service..*)")
    public void inServiceLayer() {}

    @Pointcut("within(kms.bootcamp.footballturfmanagementservice.repository..*)")
    public void inDataAccessLayer() {}

    @Around(value = "inControllerLayer() || inServiceLayer() || inDataAccessLayer()")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("Method '" + joinPoint.getSignature().toShortString() + "' execution time = [" + elapsedTime + "] ms.");
        }
    }

    @Around(value = "inControllerLayer()")
    public Object validateHeader(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpBase request = null;
        HttpBase response = new HttpBase();
        StringBuilder errorMessage = new StringBuilder();
        try {
            request = (HttpBase) joinPoint.getArgs()[0];
            Header header = request.getHeader();
            if (Objects.isNull(header)) {
                log.error("header can not be empty");
                errorMessage.append("header can not be empty");
            } else {
                if (Objects.isNull(header.getUserId())) {
                    log.error("userId can not be empty");
                    errorMessage.append("userId can not be empty");
                }
                if (Objects.isNull(header.getUserName())) {
                    log.error("userName can not be empty");
                    errorMessage.append("userName can not be empty");
                }
            }
            // Validate fail
            if (errorMessage.length() > 0) {
                Header responseHeader = new Header();
                responseHeader.setSuccess(false);
                responseHeader.setStatusCode(StatusCodeConstant.FAIL);
                responseHeader.setStatusMessage(errorMessage.toString());
                response.setHeader(responseHeader);
                return ResponseEntity.ok(response);
            }

            Object output = joinPoint.proceed();
//            if (output instanceof HttpBase) {
//                response = (HttpBase) output;
//            }
            return output;
        } catch (Exception e) {
            Header header = new Header();
            header.setSuccess(false);
            header.setStatusCode(StatusCodeConstant.FAIL);
            header.setStatusMessage(e.getMessage());
            response.setHeader(header);
            return ResponseEntity.ok(response);
        }
    }
}
