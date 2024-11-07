package com.nhnacademy.springcorefinal.price.formatter;

import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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

}
