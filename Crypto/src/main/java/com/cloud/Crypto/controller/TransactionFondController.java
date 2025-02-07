package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.TransactionFondService;
import lombok.AllArgsConstructor;
import model.TransactionFond;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionFondController {

    private final TransactionFondService transactionFondService;

    @GetMapping("/")
    public List<TransactionFond> getAll() {
        return transactionFondService.getAll();
    }

    @GetMapping("/filtre")
    public List<TransactionFond> getAllFiltre(@RequestBody Date dateMax) {
        return transactionFondService.getAllFiltre(dateMax);
    }
}
