package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.TypeTransactionService;
import model.TypeTransaction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeTransaction")
@AllArgsConstructor
public class TypeTransactionController {

    private final TypeTransactionService typeTransactionService;

    @GetMapping("/{id}")
    public TypeTransaction getTypeTransactionById(@PathVariable int id) {
        return typeTransactionService.getTypeTransactionById(id);
    }

    @GetMapping
    public List<TypeTransaction> getAllTypeTransactions() {
        return typeTransactionService.getAllTypeTransactions();
    }
}
