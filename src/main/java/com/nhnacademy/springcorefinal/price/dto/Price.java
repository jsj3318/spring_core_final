package com.nhnacademy.springcorefinal.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Price {
    @JsonProperty("순번")
    private long id;
    @JsonProperty("지자체명")
    private String city;
    @JsonProperty("업종")
    private String sector;
    @JsonProperty("단계")
    private int grade;
    @JsonProperty("구간시작(세제곱미터")
    private int unitStart;
    @JsonProperty("구간끝(세제곱미터)")
    private int unitEnd;
    @JsonProperty("구간금액(원)")
    private int unitPrice;
    @JsonProperty("단계별 기본요금(원)")
    private String gradePrice;

    public Price(long id, String city, String sector, int grade, int unitStart, int unitEnd, int unitPrice, String gradePrice) {
        this.id = id;
        this.city = city;
        this.sector = sector;
        this.grade = grade;
        this.unitStart = unitStart;
        this.unitEnd = unitEnd;
        this.unitPrice = unitPrice;
        this.gradePrice = gradePrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return id == price.id && grade == price.grade && unitStart == price.unitStart && unitEnd == price.unitEnd && unitPrice == price.unitPrice && Objects.equals(city, price.city) && Objects.equals(sector, price.sector) && Objects.equals(gradePrice, price.gradePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, sector, grade, unitStart, unitEnd, unitPrice, gradePrice);
    }
}
