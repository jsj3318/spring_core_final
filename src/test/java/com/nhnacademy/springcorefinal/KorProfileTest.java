package com.nhnacademy.springcorefinal;

import com.nhnacademy.springcorefinal.price.formatter.KoreanOutputFormatter;
import com.nhnacademy.springcorefinal.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false"
})
@ActiveProfiles("kor")
public class KorProfileTest {

    @Autowired
    private OutPutFormatter outPutFormatter;

    @Test
    void beanTest() {
        assertInstanceOf(KoreanOutputFormatter.class, outPutFormatter);
    }

}
