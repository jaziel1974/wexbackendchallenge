package com.jazieloliveira.wexbackendchallenge.service.quote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConvertedTransactionRequestDto(
        Long id,
        String description,
        @JsonFormat(pattern="yyyy-MM-dd") LocalDate transactionDate,
        BigDecimal purchaseAmount) {

}
