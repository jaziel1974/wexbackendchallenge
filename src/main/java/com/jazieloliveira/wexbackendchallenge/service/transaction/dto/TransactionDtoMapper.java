package com.jazieloliveira.wexbackendchallenge.service.transaction.dto;

import com.jazieloliveira.wexbackendchallenge.model.TransactionModel;

public class TransactionDtoMapper {

    public static TransactionModel toModel(TransactionRequestDto transactionRequestDto) {
        return new TransactionModel(transactionRequestDto.description(), transactionRequestDto.purchaseAmount());
    }

    public static TransactionResponseDto toResponseDto(TransactionModel transactionModel) {
        return new TransactionResponseDto(transactionModel.getId(), transactionModel.getDescription(), transactionModel.getTransactionDate(), transactionModel.getPurchaseAmount());
    }
}