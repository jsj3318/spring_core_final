package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@RequiredArgsConstructor
@Component
public class CsvDataParser implements DataParser{

    private final FileProperties fileProperties;

    @Value("${bill-total.city}")
    private String str_city;
    @Value("${bill-total.sector}")
    private String str_sector;
    @Value("${bill-total.unitPrice}")
    private String str_unit;
    @Value("${bill-total.billTotal}")
    private String str_total;

    @Override
    public String billTotal(String city, String sector, int usage) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {

                // 시티, 업종, 사용량으로 검색
                if(nextLine[1].trim().equals(city) && nextLine[2].trim().equals(sector)
                    && usage >= Integer.parseInt(nextLine[4]) && usage <= Integer.parseInt(nextLine[5]) ){

                    return  str_city + ": " + nextLine[1].trim() + ", " +
                            str_sector + ": " + nextLine[2].trim() + ", " +
                            str_unit + ": " + nextLine[6].trim() + ", " +
                            str_total + ": " + (Integer.parseInt(nextLine[6]) * usage);

                }

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return "일치하는 정보를 찾을 수 없습니다.";
    }

    @Override
    public Price price(String city, String sector) {

        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath())))) {
            String[] nextLine;
            reader.readNext(); // 헤더 스킵
            while ((nextLine = reader.readNext()) != null) {

                if(nextLine[1].trim().equals(city) && nextLine[2].trim().equals(sector)){
                    return new Price(
                            Long.parseLong(nextLine[0]),
                            nextLine[1].trim(),
                            nextLine[2].trim(),
                            Integer.parseInt(nextLine[6])
                    );
                }

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
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
