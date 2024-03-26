package com.jazieloliveira.wexbackendchallenge.service.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseDto(
        Long id,
        String description,
        @JsonFormat(pattern="yyyy-MM-dd") LocalDate transactionDate,
        BigDecimal purchaseAmount) {

}
