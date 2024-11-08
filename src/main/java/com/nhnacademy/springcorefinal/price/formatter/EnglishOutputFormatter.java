package com.nhnacademy.springcorefinal.price.formatter;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Profile("!kor")
public class EnglishOutputFormatter implements OutPutFormatter{

    @Override
    public String noData() {
        return "there is no data.";
    }

    @Override
    public String wrongUsage() {
        return "usage out of range.";
    }

    public EnglishOutputFormatter() {
        log.info("english format selected.");
    }

    @Override
    public String format(Price price, int usage) {
        return "city: " + price.getCity() +
                ", sector: " + price.getSector() +
                ", unit price(won): " + price.getUnitPrice() +
                ", bill total(won): " + (usage * price.getUnitPrice());
    }

    @Override
    public String login(Account account) {
        // 로그인 정보 일치하지 않음
        if(account == null){
            throw new IllegalArgumentException("id or password not correct");
        }

        return String.format("Login Success! [Account: id=%d, password:%s, name=%s]",
                account.getId(), account.getPassword(), account.getName());
    }

    @Override
    public String alreadyLogin() {
        throw new RuntimeException("You are already login");
    }

    @Override
    public String logout() {
        return "good bye~!";
    }

    @Override
    public String currentUser(Account account) {
        if(account == null){
            return "You must login first.";
        }
        return String.format("Account[id=%d, password:%s, name=%s]",
                account.getId(), account.getPassword(), account.getName() );
    }

    @Override
    public String price(List<Price> priceList) {

        StringBuilder sb = new StringBuilder();

        for(Price price : priceList){
            sb.append(price.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

}
