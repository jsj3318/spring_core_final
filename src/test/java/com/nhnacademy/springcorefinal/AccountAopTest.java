package com.nhnacademy.springcorefinal;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.nhnacademy.springcorefinal.account.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false"
})
public class AccountAopTest {

    @Autowired
    private AuthenticationService authenticationService;

    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    public void setUp() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        listAppender = new ListAppender<>();
        listAppender.setContext(loggerContext);
        listAppender.start();
        loggerContext.getLogger("com.nhnacademy.springcorefinal.account.aop.AccountAop").addAppender(listAppender);
    }

    @Test
    public void testLoggingAspect() {
        authenticationService.login(1L, "1");
        authenticationService.logout();

        List<ILoggingEvent> logsList = listAppender.list;
        assertThat(logsList).isNotEmpty();

        assertTrue(logsList.get(0).getFormattedMessage().contains("Login"));
        assertTrue(logsList.get(0).getFormattedMessage().contains("1"));

        assertTrue(logsList.get(1).getFormattedMessage().contains("Logout"));
    }

    @Test
    public void isAop() {
        assertTrue(AopUtils.isAopProxy(authenticationService));
    }

}