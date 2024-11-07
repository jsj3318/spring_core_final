package com.nhnacademy.springcorefinal.common.dataparser;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.price.dto.Price;

import java.util.List;

public interface DataParser {

    Price price(String city, String sector);

    List<Account> accounts();

    List<String> sectors(String city);

    List<String> cities();
}
