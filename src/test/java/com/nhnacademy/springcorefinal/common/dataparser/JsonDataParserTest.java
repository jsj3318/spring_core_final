package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JsonDataParserTest {

    FileProperties fileProperties = new FileProperties("json", "Tariff.json", "account.json");

    JsonDataParser jsonDataParser = new JsonDataParser(fileProperties);


    @BeforeEach
    void before(){
        jsonDataParser.setStr_city("지자체명");
        jsonDataParser.setStr_sector("업종");
        jsonDataParser.setStr_unit("구간금액(원)");
        jsonDataParser.setStr_total("총금액(원)");
    }

    @Test
    void price() {
        Price price = jsonDataParser.price("광주시", "가정용");

        log.info(price.toString());

        assertEquals(400, price.getUnitPrice());
    }

    @Test
    void accounts() {
        List<Account> accountList = jsonDataParser.accounts();

        for(Account account : accountList){
            log.info(account.toString());
        }

        assertEquals(10, accountList.size());
    }

    @Test
    void sectors() {
        List<String> sectorList = jsonDataParser.sectors("동두천시");
        for(String sector : sectorList){
            log.info(sector);
        }
        log.info("sectorList size: {}", sectorList.size());

        assertEquals(true, sectorList.containsAll(List.of(new String[]{
                "가정용", "일반용", "일반용(미)", "전용공업용", "발전용"
        })));
    }

    @Test
    void cities() {
        List<String> cityList = jsonDataParser.cities();

        for(String city : cityList){
            log.info(city);
        }

        assertEquals(true, cityList.containsAll(
                List.of(new String[]{"동두천시", "파주시", "광주시", "나주시", "완도군", "사천시"})
        ));
    }

    @Test
    void billTotal() {
        String actual = jsonDataParser.billTotal("광주시", "일반용", 200);
        String expected = "지자체명: 광주시, 업종: 일반용, 구간금액(원): 1250, 총금액(원): " + (1250 * 200);
        assertEquals(expected, actual);
    }

}