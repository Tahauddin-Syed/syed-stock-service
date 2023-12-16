package com.tahauddin.syed;

import com.tahauddin.syed.dto.StockTickDTO;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class StockServiceTest {


    private RSocketRequester rSocketRequester;

    @Autowired
    private RSocketRequester.Builder builder;

    @BeforeAll
    void beforeAll() {
        this.rSocketRequester = builder
                .transport(TcpClientTransport.create("localhost", 6565));
    }

    @Test
    void testOne() {
        Flux<StockTickDTO> stockTickDTOFlux = rSocketRequester.route("stock.service")
                .retrieveFlux(StockTickDTO.class)
                .doOnNext(l -> log.info("Stock Service Recieved :: {}", l))
                .take(12);

        StepVerifier.create(stockTickDTOFlux)
                .expectNextCount(12)
                .verifyComplete();
    }


}
