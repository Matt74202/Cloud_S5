package com.cloud.Crypto.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloud.Crypto.repository.TransactionCryptoRepository;

import lombok.AllArgsConstructor;
import model.TransactionCrypto;

@Service
@AllArgsConstructor
public class TransactionCryptoService {

    private final TransactionCryptoRepository transactionCryptoRepository;

    public List<TransactionCrypto> getAllTransactions() {
        return transactionCryptoRepository.getAll();
    }

    public List<TransactionCrypto> getFilteredTransactions(Date dateMax) {
        return transactionCryptoRepository.getAllFiltre(dateMax);
    }

    public List<TransactionCrypto> getAllTransactionsFiltre(Integer idCrypto, Integer idType, Integer idUtilisateur) {
        return transactionCryptoRepository.getAllFiltre(idCrypto, idType, idUtilisateur);
    }

}
