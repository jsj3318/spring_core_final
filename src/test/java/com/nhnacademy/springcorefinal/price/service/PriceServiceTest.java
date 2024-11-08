package com.nhnacademy.springcorefinal.price.service;

import com.nhnacademy.springcorefinal.common.dataparser.DataParser;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.nhnacademy.springcorefinal.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false"
})
@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Spy
    DataParser dataParser;
    @Spy
    OutPutFormatter outPutFormatter;

    @InjectMocks
    PriceService priceService;


    @Test
    void billTotal() {


        when(dataParser.price(any(),any())).thenReturn(
                List.of(
                        new Price(1L, "광주시", "일반용", 1, 1, 10, 1000, ""),
                        new Price(2L, "광주시", "일반용", 2, 11, 20, 2000, ""),
                        new Price(3L, "광주시", "일반용", 3, 21, 99999, 3000, "")
                )
        );

        when(outPutFormatter.format(any(Price.class),any(Integer.class))).thenReturn(String.valueOf(15 * 2000 ));

        String res = priceService.billTotal("광주시", "일반용", 15);
        assertEquals(true, res.contains(String.valueOf( 15*2000 )));

    }
}