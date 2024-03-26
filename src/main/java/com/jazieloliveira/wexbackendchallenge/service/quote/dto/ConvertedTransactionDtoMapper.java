package com.jazieloliveira.wexbackendchallenge.service.quote.dto;

import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionResponseDto;

public class ConvertedTransactionDtoMapper {
    public static ConvertedTransactionRequestDto toConvertedTransactionRequestDto(TransactionResponseDto transaction) {
        return new ConvertedTransactionRequestDto(transaction.id(), transaction.description(), transaction.transactionDate(), transaction.purchaseAmount());
    }
}