package com.nhnacademy.springcorefinal.price.formatter;

import com.nhnacademy.springcorefinal.account.dto.Account;
import com.nhnacademy.springcorefinal.price.dto.Price;

import java.util.List;

public interface OutPutFormatter {

    // bill-total
    String format(Price price, int usage);

    // no city / sector
    String noData();

    // bill-total usage 범위 나감
    String wrongUsage();

    // login
    String login(Account account);

    // already login
    String alreadyLogin();

    // logout
    String logout();

    // current-user
    String currentUser(Account account);

    // price
    String price(List<Price> priceList);

}
