package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.CryptoService;
import model.Crypto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @PostMapping("/generer")
    public ResponseEntity<Void> generateCryptos() {
        cryptoService.generateAndInsertCryptos();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Crypto>> getAllCryptos() {
        List<Crypto> cryptos = cryptoService.getAllCryptos();
        return ResponseEntity.ok(cryptos);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Crypto>> getAllLatestCryptos() {
        List<Crypto> cryptos = cryptoService.getAllLatestCryptos();
        return ResponseEntity.ok(cryptos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crypto> getCryptoById(@PathVariable int id) {
        Crypto crypto = cryptoService.getCryptoById(id);
        return crypto != null ? ResponseEntity.ok(crypto) : ResponseEntity.notFound().build();
    }
}
