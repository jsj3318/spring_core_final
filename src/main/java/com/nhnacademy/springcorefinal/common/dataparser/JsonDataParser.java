package com.nhnacademy.springcorefinal.common.dataparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
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
@ConditionalOnProperty(name="file.type", havingValue = "json")
public class JsonDataParser implements DataParser{

    @Autowired
    private final FileProperties fileProperties;

    @Value("${bill-total.city}")
    private String str_city;
    @Value("${bill-total.sector}")
    private String str_sector;
    @Value("${bill-total.unitPrice}")
    private String str_unit;
    @Value("${bill-total.billTotal}")
    private String str_total;

    private List<Account> accountList;
    private List<Price> priceList;

    public JsonDataParser(FileProperties fileProperties) {

        log.info("json으로 파일 로딩");

        this.fileProperties = fileProperties;

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
    public Price price(String city, String sector) {


        for(Price price : priceList){
            if(price.getCity().equals(city) && price.getSector().equals(sector)){
                return price;
            }

        }


        return null;
    }

    @Override
    public List<Account> accounts() {
        return accountList;
    }

    @Override
    public List<String> sectors(String city) {
        List<String> sectorList = new ArrayList<>();

        for(Price price : priceList){
            if(!sectorList.contains(price.getSector())){
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

    @Override
    public String billTotal(String city, String sector, int usage) {
        for(Price price : priceList){
            // 시티, 업종, 사용량으로 검색
            if( price.getCity().equals(city) && price.getSector().equals(sector) &&
                    usage >= price.getUnitStart() && usage <= price.getUnitEnd() ){
                return  str_city + ": " + price.getCity() + ", " +
                        str_sector + ": " + price.getSector() + ", " +
                        str_unit + ": " + price.getUnitPrice() + ", " +
                        str_total + ": " + (price.getUnitPrice() * usage);

            }
        }

        return "일치하는 정보를 찾을 수 없습니다.";
    }
}
