package com.nhnacademy.springcorefinal.price.formatter;

import com.nhnacademy.springcorefinal.price.dto.Price;

public interface OutPutFormatter {
    String format(Price price, int usage);
}
