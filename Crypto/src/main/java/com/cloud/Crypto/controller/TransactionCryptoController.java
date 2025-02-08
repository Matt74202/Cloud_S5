package com.cloud.Crypto.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.cloud.Crypto.service.TransactionCryptoService;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import model.TransactionCrypto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction/crypto")

public class TransactionCryptoController {

    private final TransactionCryptoService transactionCryptoService;

    @GetMapping("/")
    public ResponseEntity<List<TransactionCrypto>> getAllTransactions() {
        List<TransactionCrypto> transactions = transactionCryptoService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/filtre/date")
    public ResponseEntity<List<TransactionCrypto>> getFilteredTransactions(
        @RequestParam("dateMax") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateMax) {
        List<TransactionCrypto> transactions = transactionCryptoService.getFilteredTransactions(dateMax);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/filtre")
    public ResponseEntity<List<TransactionCrypto>> getFilteredTransactions(
            @RequestParam(value = "idCrypto", defaultValue = "0") Integer idCrypto,
            @RequestParam(value = "idType", defaultValue = "0") Integer idType,
            @RequestParam(value = "idUtilisateur", defaultValue = "0") Integer idUtilisateur) {
        
        // Call the service method with the filtered parameters
        List<TransactionCrypto> transactions = transactionCryptoService.getAllTransactionsFiltre(idCrypto, idType, idUtilisateur);
        
        // Return the filtered transactions
        return ResponseEntity.ok(transactions);
    }


}

