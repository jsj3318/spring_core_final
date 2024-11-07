package com.nhnacademy.springcorefinal.account.service;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.dataparser.DataParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@RequiredArgsConstructor
public class AuthenticationService {

    private final DataParser dataParser;

    // 현재 로그인 된 계정 (null -> 로그인 상태가 아님)
    private Account currentAccount = null;


    public Account login(Long id, String password) {
        // 로그인: 계정 목록에서 id와 암호가 일치하는 계정 정보를 찾아서 반환한다.
        for(Account account : dataParser.accounts()){
            if(account.getId() == id && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }

    public void logout() {
        // 로그아웃: 현재 계정을 제거한다.
        currentAccount = null;

    }


}
