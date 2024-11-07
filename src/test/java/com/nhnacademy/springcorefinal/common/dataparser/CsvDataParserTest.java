package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CsvDataParserTest {


    FileProperties fileProperties = new FileProperties("csv", "Tariff.csv", "account.csv");

    CsvDataParser csvDataParser = new CsvDataParser(fileProperties);


    @BeforeEach
    void before(){
        csvDataParser.post();
    }

    @Test
    void price() {
        List<Price> prices = csvDataParser.price("광주시", "가정용");

        log.info(prices.toString());

        assertEquals(3, prices.size());
    }

    @Test
    void accounts() {
        List<Account> accountList = csvDataParser.accounts();

        for(Account account : accountList){
            log.info(account.toString());
        }

        assertEquals(10, accountList.size());
    }

    @Test
    void sectors() {
        List<String> sectorList = csvDataParser.sectors("동두천시");
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
        List<String> cityList = csvDataParser.cities();

        for(String city : cityList){
            log.info(city);
        }

        assertEquals(true, cityList.containsAll(
                List.of(new String[]{"동두천시", "파주시", "광주시", "나주시", "완도군", "사천시"})
        ));
    }



}