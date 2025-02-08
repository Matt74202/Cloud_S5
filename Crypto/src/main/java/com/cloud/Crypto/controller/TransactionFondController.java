package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.TransactionFondService;
import lombok.AllArgsConstructor;
import model.TransactionFond;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction/fond")
public class TransactionFondController {

    private final TransactionFondService transactionFondService;

    @GetMapping("/")
    public ResponseEntity<List<TransactionFond>> getAll() {
        List<TransactionFond> transactionFonds= transactionFondService.getAll();
        return ResponseEntity.ok(transactionFonds);
    }

    @GetMapping("/non-valides")
    public ResponseEntity<List<TransactionFond>> getAllNonValides() {
        List<TransactionFond> transactionFonds= transactionFondService.getAllNonValides();
        return ResponseEntity.ok(transactionFonds);
    }

    @GetMapping("/filtre")
    public ResponseEntity<List<TransactionFond>> getAllFiltre(@RequestBody Date dateMax) {
        List<TransactionFond> transactionFonds= transactionFondService.getAllFiltre(dateMax);
        return ResponseEntity.ok(transactionFonds);
    }

    @GetMapping("/{id}/valider")
    public ResponseEntity<TransactionFond> validerTransaction(@PathVariable int idTransaction) {
        TransactionFond validatedTransaction = transactionFondService.validerTransaction(idTransaction);
        return ResponseEntity.ok(validatedTransaction);
    }
}
