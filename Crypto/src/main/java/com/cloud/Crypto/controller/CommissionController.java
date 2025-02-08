package com.cloud.Crypto.controller;

import model.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cloud.Crypto.service.CommissionService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/commission")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @PostMapping("/")
    public ResponseEntity<Commission> insertCommission(@RequestBody Commission commission) {
        commissionService.insert(commission);
        return ResponseEntity.ok(commission);
    }

    @GetMapping("/")
    public ResponseEntity<List<Commission>> getAllCommissions() {
        List<Commission> commissions= commissionService.getAll();
        return ResponseEntity.ok(commissions);
    }

    @GetMapping("/filtre")
    public ResponseEntity<List<Commission>> getAllCommissionsByDate(@RequestParam("dateMax") Date dateMax) {
        List<Commission> commissions= commissionService.getAllByDate(dateMax);
        return ResponseEntity.ok(commissions);
    }
}

