package com.nhnacademy.springcorefinal.account.aop;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.account.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AccountAop {

    @Pointcut("execution(* com.nhnacademy.springcorefinal.account.service.AuthenticationService.login(..))")
    void loginPointcut(){}

    @Pointcut("execution(* com.nhnacademy.springcorefinal.account.service.AuthenticationService.logout())")
    void logoutPointcut(){}


    @Before("loginPointcut()")
    public void LogBeforeLogin(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long id = (Long) args[0];
        String password = (String) args[1];

        log.info("Login[id={}, password={}]", id, password);
    }

    @Before("logoutPointcut()")
    public void LogBeforeLogout(JoinPoint joinPoint){
        AuthenticationService authenticationService = (AuthenticationService) joinPoint.getTarget();
        Account account = authenticationService.getCurrentAccount();
        if(account == null){
            log.info("Logout by null account");
        }
        else{
            log.info("Logout[id={}, password={}]", account.getId(), account.getPassword());
        }
    }


}