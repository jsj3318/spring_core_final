package com.nhnacademy.springcorefinal.common.runner;

import com.nhnacademy.springcorefinal.common.dataparser.DataParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final DataParser dataParser;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        log.info(dataParser.billTotal("광주시", "일반용", 200));
    }
}
