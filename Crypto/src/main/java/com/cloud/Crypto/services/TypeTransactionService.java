package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.TypeTransactionRepository;
import model.TypeTransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeTransactionService {

    private final TypeTransactionRepository typeTransactionRepository;

    public TypeTransaction getTypeTransactionById(int id) {
        return typeTransactionRepository.getById(id);
    }

    public List<TypeTransaction> getAllTypeTransactions() {
        return typeTransactionRepository.getAll();
    }
}
