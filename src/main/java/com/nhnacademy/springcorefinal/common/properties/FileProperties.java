package com.nhnacademy.springcorefinal.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("file")
@AllArgsConstructor
public class FileProperties {
    private String type;
    private String pricePath;
    private String accountPath;
    
}
