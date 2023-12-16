package com.tahauddin.syed.service;

import com.tahauddin.syed.dto.StockTickDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.tahauddin.syed.constants.StockConstant.*;

@Service
@Slf4j
public class StockService {

    public Flux<StockTickDTO> getStockPrices() {
        return Flux.interval(Duration.ofSeconds(2))
                .flatMap(i -> Flux.just(AMAZON_STOCK, APPLE_STOCK, FACEBOOK_STOCK))
                .map(s -> new StockTickDTO(s.getCode(), s.getPrice()));
    }


}
