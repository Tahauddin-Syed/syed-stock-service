package com.tahauddin.syed.controller;

import com.tahauddin.syed.dto.StockTickDTO;
import com.tahauddin.syed.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;


    @MessageMapping("stock.service")
    public Flux<StockTickDTO> getStocks() {
        return stockService.getStockPrices();
    }

    @ConnectMapping
    public Mono<Void> connectionSetup() {
        log.info("Connection Setup End Point");
        return Mono.empty();
    }



}
