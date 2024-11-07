package com.nhnacademy.springcorefinal.price.formatter;

import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!kor")
public class EnglishOutputFormatter implements OutPutFormatter{

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

}
