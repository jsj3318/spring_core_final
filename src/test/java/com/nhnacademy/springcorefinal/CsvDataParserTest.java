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
    }

    @Test
    void cities() {
    }
}