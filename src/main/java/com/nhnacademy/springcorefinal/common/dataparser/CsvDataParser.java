package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.common.properties.FileProperties;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
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
@ConditionalOnProperty(name="file.type", havingValue = "csv")
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

    private List<Account> accountList = new ArrayList<>();
    private List<Price> priceList = new ArrayList<>();

    // 생성자에서 accountlist, pricelist 받아오기
    public CsvDataParser(FileProperties fileProperties) {

        log.info("csv로 파일 로딩");

        this.fileProperties = fileProperties;

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

}
