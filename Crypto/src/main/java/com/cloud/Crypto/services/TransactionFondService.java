package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.TransactionFondRepository;
import lombok.AllArgsConstructor;
import model.TransactionFond;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionFondService {
    private final TransactionFondRepository transactionFondRepository;

    public List<TransactionFond> getAll() {
        return transactionFondRepository.getAll();
    }
    
    public List<TransactionFond> getAllFiltre(Date dateMax) {
        return transactionFondRepository.getAllFiltre(dateMax);
    }

    public List<TransactionFond> getAllNonValides(){
        return transactionFondRepository.getAllNonValides();
    }

    public TransactionFond validerTransaction(int idTransaction) {
        return transactionFondRepository.valider(idTransaction);
    }

}
