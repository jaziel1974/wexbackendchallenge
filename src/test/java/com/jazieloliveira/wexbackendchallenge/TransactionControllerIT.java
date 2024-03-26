package com.jazieloliveira.wexbackendchallenge;

import com.jazieloliveira.wexbackendchallenge.service.quote.dto.ConvertedTransactionResponseDto;
import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionRequestDto;
import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TransactionControllerIT {

    @Autowired
    WebTestClient webTestClient;

    private AtomicReference<TransactionResponseDto> transaction;

    @Test
    void createTransaction_Valid() throws Exception {
        //execute test
        webTestClient
                .post()
                .uri("/transaction")
                .bodyValue(new TransactionRequestDto("test", new BigDecimal("100.001")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TransactionResponseDto.class)
                .value(response -> {
                    // assert response
                    assert response.description().equals("test");
                    assert response.purchaseAmount().equals(new BigDecimal("100.00"));
                });
    }

    @Test
    void createTransaction_DescriptionInvalid() throws Exception {
        String descriptionInvalid = "Invalid".repeat(10);
        //execute test
        webTestClient
                .post()
                .uri("/transaction")
                .bodyValue(new TransactionRequestDto(descriptionInvalid, new BigDecimal("0.001")))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void getTransaction_Valid() throws Exception {
        transaction = new AtomicReference<>();

        //prepare
        webTestClient
                .post()
                .uri("/transaction")
                .bodyValue(new TransactionRequestDto("test", new BigDecimal("100.001")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TransactionResponseDto.class)
                .value(transaction::set);

        //execute test
        webTestClient
                .get()
                .uri("/transaction/" + transaction.get().id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConvertedTransactionResponseDto.class)
                .value(response -> {
                    // assert response
                    assert response.description().equals("test");
                });
    }

    @Test
    void getTransaction_NotFound() throws Exception {
        //execute test
        webTestClient
                .get()
                .uri("/transaction/0")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void getConvertedTransaction_Valid() throws Exception {
        //prepare
        transaction = new AtomicReference<>();

        webTestClient
                .post()
                .uri("/transaction")
                .bodyValue(new TransactionRequestDto("test", new BigDecimal("100.001")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TransactionResponseDto.class)
                .value(transaction::set);

        //execute test
        webTestClient
                .get()
                .uri("/transaction/" + transaction.get().id() + "/converted?country=Brazil&currency=Real")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConvertedTransactionResponseDto.class)
                .value(response -> {
                    // assert response
                    assert response.description().equals("test");
                });
    }

    @Test
    void getConvertedTransaction_Invalid() throws Exception {
        //prepare
        transaction = new AtomicReference<>();

        webTestClient
                .post()
                .uri("/transaction")
                .bodyValue(new TransactionRequestDto("test", new BigDecimal("100.001")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TransactionResponseDto.class)
                .value(transaction::set);

        //execute test
        webTestClient
                .get()
                .uri("/transaction/" + transaction.get().id() + "/converted?country=Brazil&currency=REAL")
                .exchange()
                .expectStatus().isBadRequest();
    }
}