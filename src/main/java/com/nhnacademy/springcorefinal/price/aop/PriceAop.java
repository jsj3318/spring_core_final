package com.nhnacademy.springcorefinal.price.aop;

import com.nhnacademy.springcorefinal.shell.MyCommands;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class PriceAop {


    @Around("@annotation(com.nhnacademy.springcorefinal.common.annotation.PriceAopAnnotation)")
    public Object logPriceAop(ProceedingJoinPoint joinPoint) throws Throwable{

        Object target = joinPoint.getTarget();

        MyCommands instance = (MyCommands) target;
        if(instance.getAuthenticationService().getCurrentAccount() == null){
            throw new RuntimeException("로그인 하지 않은 사용자의 접근");
        }

        String accountName = instance.getAuthenticationService().getCurrentAccount().getName();
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 들어오는 요청 로그
        log.info("<----- {} class {}({})",
                accountName,
                methodName,
                Arrays.toString(args));

        // 실제 메서드 실행
        Object result = joinPoint.proceed();

        // 결과 로그
        log.info("-----> {} class {}({})",
                accountName,
                methodName,
                result);

        return result;
    }

}