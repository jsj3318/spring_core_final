package com.nhnacademy.springcorefinal.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @JsonProperty("아이디")
    private long id;
    @JsonProperty("비밀번호")
    private String password;
    @JsonProperty("이름")
    private String name;


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
