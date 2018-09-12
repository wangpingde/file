package com.hoperun.qkl.fileserve.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private final ObjectMapper mapper;

    @Autowired
    public WebLogAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Pointcut("execution(* com.hoperun.qkl.fileserve.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        for (Object object : joinPoint.getArgs()) {
            if (
                    object instanceof MultipartFile
                            || object instanceof HttpServletRequest
                            || object instanceof HttpServletResponse
                    ) {
                continue;
            }
            try {
                if (log.isDebugEnabled()) {
                    log.debug(
                            joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
                                    + " : request parameter : " + mapper.writeValueAsString(object)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) throws Throwable {
        if (response != null) {
            log.debug("response parameter : " + mapper.writeValueAsString(response));
        }
    }

}
