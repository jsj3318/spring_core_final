package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name="file.type", havingValue = "csv")
public class CsvDataParser implements DataParser{

    private final FileProperties fileProperties;

    private List<Account> accountList = new ArrayList<>();
    private List<Price> priceList = new ArrayList<>();

    @PostConstruct
    public void post(){
        log.info("csv 파일 로딩");

        // accountList
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getAccountPath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {

                accountList.add(
                        new Account(
                                Long.parseLong(nextLine[0]),
                                nextLine[1].trim(),
                                nextLine[2].trim()
                        )
                );

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        // priceList
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {
                priceList.add(
                        new Price(
                                Long.parseLong(nextLine[0]),
                                nextLine[1].trim(),
                                nextLine[2].trim(),
                                Integer.parseInt(nextLine[3]),
                                Integer.parseInt(nextLine[4]),
                                Integer.parseInt(nextLine[5]),
                                Integer.parseInt(nextLine[6]),
                                nextLine[7].trim()
                        )
                );

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
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

}
