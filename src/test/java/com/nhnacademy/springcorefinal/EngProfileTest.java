package com.nhnacademy.springcorefinal;

import com.nhnacademy.springcorefinal.price.formatter.EnglishOutputFormatter;
import com.nhnacademy.springcorefinal.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=price.csv",
        "file.account-path=account.csv"
})
@ActiveProfiles("eng")
public class EngProfileTest {

    @Autowired
    private OutPutFormatter outPutFormatter;

    @Test
    void beanTest() {
        assertInstanceOf(EnglishOutputFormatter.class, outPutFormatter);
    }


}
