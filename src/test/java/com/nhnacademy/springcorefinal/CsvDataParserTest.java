package com.nhnacademy.springcorefinal;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.dataparser.CsvDataParser;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CsvDataParserTest {


    FileProperties fileProperties = new FileProperties();

    CsvDataParser csvDataParser = new CsvDataParser(fileProperties);

    @BeforeEach
    void before(){
        fileProperties.setType("csv");
        fileProperties.setAccountPath("account.csv");
        fileProperties.setPricePath("Tariff.csv");
    }

    @Test
    void price() {
        
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