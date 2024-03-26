package com.jazieloliveira.wexbackendchallenge.service.transaction;

import com.jazieloliveira.wexbackendchallenge.model.TransactionModel;
import com.jazieloliveira.wexbackendchallenge.repository.TransactionRepository;
import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionDtoMapper;
import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionRequestDto;
import com.jazieloliveira.wexbackendchallenge.service.transaction.dto.TransactionResponseDto;

import java.util.List;
import java.util.stream.Collectors;


public class TransactionService {
    private final TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponseDto create(TransactionRequestDto transactionRequest) {
        TransactionModel transactionModel = TransactionDtoMapper.toModel(transactionRequest);
        return TransactionDtoMapper.toResponseDto(transactionRepository.save(transactionModel));
    }

    public List<TransactionResponseDto> findAll() {
        List<TransactionModel> transactions = transactionRepository.findAll();
        return transactions.stream().map(TransactionDtoMapper::toResponseDto).collect(Collectors.toList());
    }

    public TransactionResponseDto findById(Long id) {
        return TransactionDtoMapper.toResponseDto(transactionRepository.findById(id).orElseThrow());
    }
}