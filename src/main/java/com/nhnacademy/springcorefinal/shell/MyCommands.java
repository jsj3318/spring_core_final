package com.nhnacademy.springcorefinal.shell;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.account.service.AuthenticationService;
import com.nhnacademy.springcorefinal.price.formatter.OutPutFormatter;
import com.nhnacademy.springcorefinal.price.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class MyCommands {
    private final AuthenticationService authenticationService;
    private final PriceService priceService;
    private final OutPutFormatter outPutFormatter;


    @ShellMethod()
    public String login(long id, String password) {
        // 입력 받은 정보로 로그인 시도
        Account account = authenticationService.login(id, password);

        return outPutFormatter.login(account);

    }

    @ShellMethod
    public String logout() {
        authenticationService.logout();
        return outPutFormatter.logout();
    }

    @ShellMethod
    public String currentUser() {
        return outPutFormatter.currentUser(authenticationService.getCurrentAccount());
    }

    @ShellMethod
    public String city() {
        // [광주시, 나주시]
        return "[" + String.join(", ", priceService.cities()) + "]";
    }

    @ShellMethod
    public String sector(String city) {
        // [가정용, 일반용]
        return "[" + String.join(", ", priceService.sectors(city)) + "]";
    }

    @ShellMethod
    public String price(String city, String sector) {
        return outPutFormatter.price(priceService.price(city, sector));
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return priceService.billTotal(city, sector, usage);
    }

}