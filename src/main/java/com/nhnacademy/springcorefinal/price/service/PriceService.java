package com.nhnacademy.springcorefinal.price.service;

import com.nhnacademy.springcorefinal.common.dataparser.DataParser;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.nhnacademy.springcorefinal.price.formatter.OutPutFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceService {
    private final DataParser dataParser;
    private final OutPutFormatter outPutFormatter;

    public List<String> cities() {
        return dataParser.cities();
    }

    public List<String> sectors(String city) {
        return dataParser.sectors(city);
    }

    public List<Price> price(String city, String sector) {
        return dataParser.price(city, sector);
    }

    public String billTotal(String city, String sector, int usage) {
        // city, sector에 일치하는 price들 중에서 맞는 구간을 선택하고, usage * unit
        //dataParser.price 호출 시 일치하는 price들만 반환 해 줌
        List<Price> priceList = dataParser.price(city, sector);

        // 존재하지 않는 city, sector
        if(priceList.isEmpty()){
            return outPutFormatter.noData();
        }

        for(Price price : priceList){
            if( usage >= price.getUnitStart() && usage <= price.getUnitEnd() ) {

                return outPutFormatter.format(price, usage);

            }
        }

        return outPutFormatter.wrongUsage();

    }

}
