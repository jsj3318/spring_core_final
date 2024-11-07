package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.price.dto.Price;

import java.util.List;

public interface DataParser {

    // 시티와 섹터를 넣고 price 객체 반환
    Price price(String city, String sector);

    // 계정 리스트 반환
    List<Account> accounts();

    // 섹터 리스트 반환
    List<String> sectors(String city);

    // 도시 리스트 반환
    List<String> cities();

    // 시티, 섹터, 사용량으로 요금 까지 출력
    String billTotal(String city, String sector, int usage);

}
