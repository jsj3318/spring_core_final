package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvDataParser implements DataParser{

    private final FileProperties fileProperties;


    @Override
    public Price price(String city, String sector) {

        return null;
    }

    @Override
    public List<Account> accounts() {
        List<Account> accounts = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getAccountPath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {

                accounts.add(
                        new Account( Long.parseLong(nextLine[0] ), nextLine[1].trim(), nextLine[2].trim())
                );

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    @Override
    public List<String> sectors(String city) {
        List<String> sectorList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {

                // 지자체명이 일치하고, 겹치지 않는 업종 저장
                if(nextLine[1].trim().equals(city) && !sectorList.contains(nextLine[2].trim())){
                    sectorList.add(nextLine[2].trim());

                }

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


        return sectorList;
    }

    @Override
    public List<String> cities() {
        List<String> cityList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {

                // 겹치지 않게 지자체명 저장
                if(!cityList.contains(nextLine[1].trim())){
                    cityList.add(nextLine[1].trim());
                }

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


        return cityList;
    }

}
