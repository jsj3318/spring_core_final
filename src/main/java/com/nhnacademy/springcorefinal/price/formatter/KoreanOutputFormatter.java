package com.nhnacademy.springcorefinal.price.formatter;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Profile("kor")
public class KoreanOutputFormatter implements OutPutFormatter{

    public KoreanOutputFormatter() {
        log.info("한국어 포맷 선택 됨.");
    }

    @Override
    public String format(Price price, int usage) {
        return "지자체명: " + price.getCity() +
                ", 업종: " + price.getSector() +
                ", 구간금액(원): " + price.getUnitPrice() +
                ", 총금액(원): " + (usage * price.getUnitPrice());
    }

    @Override
    public String login(Account account) {
        // 로그인 정보 일치하지 않음
        if(account == null){
            throw new IllegalArgumentException("아이디 또는 패스워드가 일치하지 않네요.");
        }

        return String.format("로그인 성공! [계정 정보: 아이디=%d, 패스워드:%s, 이름=%s]",
                account.getId(), account.getPassword(), account.getName() );
    }

    @Override
    public String logout() {
        return "잘가요..";
    }

    @Override
    public String currentUser(Account account) {
        if(account == null){
            return "로그인 부터 하세요.";
        }
        return String.format("계정 정보[아이디=%d, 패스워드:%s, 이름=%s]",
                account.getId(), account.getPassword(), account.getName() );
    }

    @Override
    public String price(List<Price> priceList) {
        // 내용이 존재하지 않는다
        if(priceList.isEmpty()){
            return "데이터가 존재하지 않습니다.";
        }

        StringBuilder sb = new StringBuilder();

        for(Price price : priceList){
            sb.append(price.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

}
