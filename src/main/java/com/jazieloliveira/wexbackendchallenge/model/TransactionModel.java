package com.jazieloliveira.wexbackendchallenge.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Table("transaction")
public class TransactionModel {
    @Id
    private Long id;
    private String description;
    @CreatedDate private LocalDate transactionDate;
    private BigDecimal purchaseAmount;

    public TransactionModel(String description, BigDecimal purchaseAmount) {
        this.description = description;
        this.purchaseAmount = purchaseAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
