package com.nhnacademy.springcorefinal.account.service;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.dataparser.DataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    DataParser dataParser;

    @InjectMocks
    AuthenticationService authenticationService;

    @BeforeEach
    void setup(){
        when(dataParser.accounts()).thenReturn(
                List.of( new Account(1L, "1", "marco") )
        );
    }

    @Test
    void login() {
        Account account = authenticationService.login(1L, "1");
        assertEquals("marco", account.getName());
    }

    @Test
    void loginFail(){
        Account account = authenticationService.login(2L, "2");
        assertEquals(null, account);
    }

    @Test
    void logout() {
        authenticationService.login(1L, "1");
        Account before = authenticationService.getCurrentAccount();

        authenticationService.logout();
        Account after = authenticationService.getCurrentAccount();

        assertAll(
                ()->{
                    assertNotNull(before);
                },
                ()->{
                    assertNull(after);
                }
        );
    }

    @Test
    void getCurrentAccount() {
        authenticationService.login(1L, "1");
        assertEquals("marco", authenticationService.getCurrentAccount().getName());
    }

}