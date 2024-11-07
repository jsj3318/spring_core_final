package com.nhnacademy.springcorefinal.price.service;

import com.nhnacademy.springcorefinal.common.dataparser.DataParser;
import com.nhnacademy.springcorefinal.price.dto.Price;
import com.nhnacademy.springcorefinal.price.formatter.OutPutFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
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

        for(Price price : dataParser.price(city, sector)){
            if(price.getCity().equals(city) && price.getSector().equals(sector) &&
                    usage >= price.getUnitStart() && usage <= price.getUnitEnd() ) {

                return outPutFormatter.format(price, usage);

            }
        }

        return "일치하는 정보를 찾을 수 없습니다.";
    }

}
