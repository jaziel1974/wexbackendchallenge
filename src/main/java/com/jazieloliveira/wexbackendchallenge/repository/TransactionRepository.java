package com.jazieloliveira.wexbackendchallenge.repository;

import com.jazieloliveira.wexbackendchallenge.model.TransactionModel;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<TransactionModel, Long> {
}
