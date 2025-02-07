package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.CryptoService;
import model.Crypto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @PostMapping("/generer")
    public ResponseEntity<List<Crypto>> generateCryptos() {
        List<Crypto> cryptos= cryptoService.generateAndInsertCryptos();
        return ResponseEntity.ok(cryptos);
    }

    @GetMapping("/")
    public ResponseEntity<List<Crypto>> getAllLatestCryptos() {
        List<Crypto> cryptos = cryptoService.getCryptoLast();
        return ResponseEntity.ok(cryptos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crypto> getCryptoById(@PathVariable int id) {
        Crypto crypto = cryptoService.getCryptoById(id);
        return crypto != null ? ResponseEntity.ok(crypto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/historique")
    public ResponseEntity<List<Crypto>> getHistorique (@PathVariable int id) {
        List<Crypto> cryptos = cryptoService.getHistorique(id);
        return ResponseEntity.ok(cryptos);
    }
}
