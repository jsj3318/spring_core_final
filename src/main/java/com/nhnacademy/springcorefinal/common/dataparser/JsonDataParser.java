package com.nhnacademy.springcorefinal.common.dataparser;

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

    private List<Account> accountList = new ArrayList<>();
    private List<Price> priceList = new ArrayList<>();

    public JsonDataParser(FileProperties fileProperties) {
        this.fileProperties = fileProperties;


    }

    @Override
    public Price price(String city, String sector) {
        return null;
    }

    @Override
    public List<Account> accounts() {
        return List.of();
    }

    @Override
    public List<String> sectors(String city) {
        return List.of();
    }

    @Override
    public List<String> cities() {
        return List.of();
    }

    @Override
    public String billTotal(String city, String sector, int usage) {
        return "";
    }
}
