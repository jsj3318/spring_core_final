package com.nhnacademy.springcorefinal.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Account {
    @JsonProperty("아이디")
    private long id;
    @JsonProperty("비밀번호")
    private String password;
    @JsonProperty("이름")
    private String name;

    public Account(long id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
