package com.jazieloliveira.wexbackendchallenge.service.quote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConvertedTransactionResponseDto(
        Long identifier,
        String description,
        @JsonFormat(pattern="yyyy-MM-dd") LocalDate transactionDate,
        BigDecimal originalTransactionAmount,
        BigDecimal exchangeRate,
        BigDecimal convertedAmount
        ) {
}