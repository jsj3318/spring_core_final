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
    @JsonProperty("구간금액(원)")
    private int unitPrice;

    public Price(long id, String city, String sector, int unitPrice) {
        this.id = id;
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
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
        return id == price.id && unitPrice == price.unitPrice && Objects.equals(city, price.city) && Objects.equals(sector, price.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, sector, unitPrice);
    }
}
