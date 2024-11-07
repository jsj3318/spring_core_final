package com.nhnacademy.springcorefinal.price.service;

import com.nhnacademy.springcorefinal.price.dto.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PriceService {


    public List<String> cities() {
        return null;
    }

    public List<String> sectors(String city) {
        return null;
    }

    public Price price(String city, String sector) {
        return null;
    }

    public String billTotal(String city, String sector, int usage) {
//        for(Price price : priceList){
//            // 시티, 업종, 사용량으로 검색
//            if( price.getCity().equals(city) && price.getSector().equals(sector) &&
//                    usage >= price.getUnitStart() && usage <= price.getUnitEnd() ){
//                return  str_city + ": " + price.getCity() + ", " +
//                        str_sector + ": " + price.getSector() + ", " +
//                        str_unit + ": " + price.getUnitPrice() + ", " +
//                        str_total + ": " + (price.getUnitPrice() * usage);
//
//            }
//        }

        return "일치하는 정보를 찾을 수 없습니다.";
    }

}
