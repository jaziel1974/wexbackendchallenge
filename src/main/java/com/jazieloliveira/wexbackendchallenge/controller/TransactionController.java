package com.jazieloliveira.wexbackendchallenge.controller;

import com.jazieloliveira.wexbackendchallenge.service.quote.ExchangeService;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.ConvertedTransactionDtoMapper;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.ConvertedTransactionRequestDto;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.ConvertedTransactionResponseDto;
import com.jazieloliveira.wexbackendchallenge.service.transaction.TransactionService;
import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;
    private final ExchangeService exchangeService;

    public TransactionController(TransactionService transactionService, ExchangeService exchangeService) {
        this.transactionService = transactionService;
        this.exchangeService = exchangeService;
    }

    @PostMapping
    @Operation(summary = "Creates a transaction")
    public ResponseEntity<Object> create(@Valid @RequestBody TransactionRequestDto transactionRequest){
        logger.info("Creating quote");

        try {
            return new ResponseEntity<>(transactionService.create(transactionRequest), null, 201);
        } catch (Exception e) {
            logger.error("Error creating quote", e);
            return new ResponseEntity<>(e.getMessage(), null, 400);
        }
    }

    @GetMapping()
    @Operation(summary = "Gets all transactions without conversion")
    public ResponseEntity<Object> find(){
        logger.info("Getting all transaction");

        try {
            return new ResponseEntity<>(transactionService.findAll(), null, 200);
        } catch (Exception e) {
            logger.error("Error finding quotes", e);
            return new ResponseEntity<>(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a transaction by id without conversion")
    public ResponseEntity<Object> find(@PathVariable("id") Long id){
        logger.info("Getting transaction");

        try {
            return new ResponseEntity<>(ConvertedTransactionDtoMapper.toConvertedTransactionRequestDto(transactionService.findById(id)), null, 200);
        } catch (Exception e) {
            logger.error("Error finding quotes", e);
            return new ResponseEntity<>(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/{id}/converted")
    @Operation(summary = "Gets a transaction by id with conversion")
    public ResponseEntity<Object> getExchange(@PathVariable("id") Long id, @RequestParam("country") String country, @RequestParam("currency") String currency){
        logger.info("Getting converted transaction");

        try {
            ConvertedTransactionRequestDto transaction = ConvertedTransactionDtoMapper.toConvertedTransactionRequestDto(transactionService.findById(id));
            ConvertedTransactionResponseDto response = exchangeService.getRate(transaction, country, currency);
            return new ResponseEntity<>(response, null, 200);
        } catch (Exception e) {
            logger.error("Error finding converted quotes", e);
            return new ResponseEntity<>(e.getMessage(), null, 400);
        }
    }
}