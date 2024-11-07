package com.nhnacademy.springcorefinal.common.dataparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name="file.type", havingValue = "json")
public class JsonDataParser implements DataParser{

    @Autowired
    private final FileProperties fileProperties;

    private List<Account> accountList;
    private List<Price> priceList;

    @PostConstruct
    public void post(){
        log.info("json 파일 로딩");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // account 불러오기
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileProperties.getAccountPath());
            accountList = objectMapper.readValue(inputStream, new TypeReference<List<Account>>() {});

            // price 불러오기
            inputStream = getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath());
            priceList = objectMapper.readValue(inputStream, new TypeReference<List<Price>>() {});

            log.info("accountList size: {}, priceList size: {}", accountList.size(), priceList.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Price> price(String city, String sector) {
        List<Price> res = new ArrayList<>();

        for(Price price : priceList){
            if(price.getCity().equals(city) && price.getSector().equals(sector)){
                res.add(price);
            }

        }

        return res;
    }

    @Override
    public List<Account> accounts() {
        return accountList;
    }

    @Override
    public List<String> sectors(String city) {
        List<String> sectorList = new ArrayList<>();

        for(Price price : priceList){
            if(price.getCity().equals(city) && !sectorList.contains(price.getSector())){
                sectorList.add(price.getSector());
            }
        }

        return sectorList;
    }

    @Override
    public List<String> cities() {
        List<String> cityList = new ArrayList<>();

        for(Price price : priceList){
            if(!cityList.contains(price.getCity())){
                cityList.add(price.getCity());
            }
        }

        return cityList;
    }

}
