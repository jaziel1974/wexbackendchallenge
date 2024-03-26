package com.jazieloliveira.wexbackendchallenge.service.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransactionRequestDto(
        @Size(max = 50) String description,
        @DecimalMin("0.01") BigDecimal purchaseAmount) {

}
