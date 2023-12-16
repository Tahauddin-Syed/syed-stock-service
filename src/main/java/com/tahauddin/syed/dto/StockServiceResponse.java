package com.tahauddin.syed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;


public class StockServiceResponse {

    private Integer price;
    private String code;
    private Integer volatility;

    public StockServiceResponse(Integer price, String code, Integer volatility) {
        this.price = price;
        this.code = code;
        this.volatility = volatility;
    }

    public Integer getPrice() {
        this.updateVolatility();
        return price;
    }

    public String getCode() {
        return code;
    }

    public Integer getVolatility() {
        return volatility;
    }

    public void updateVolatility() {
        int randomVol = ThreadLocalRandom.current().nextInt(-1 * volatility, volatility + 1);
        price += randomVol;
        price= Math.max(price, 0);
    }
}
