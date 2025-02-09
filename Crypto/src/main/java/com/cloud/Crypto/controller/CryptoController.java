package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.CryptoService;
import model.Crypto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.List;
import java.sql.Date;
import java.util.Map;
import java.text.SimpleDateFormat;  
import java.util.stream.Collectors; 


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

    @PostMapping("/analyse")
    public ResponseEntity<Double> analyser(@RequestBody Map<String, Object> filtre) {
        try {
            int idType = ((Number) filtre.get("type")).intValue();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDateMin = dateFormat.parse((String) filtre.get("dateMin"));
            java.util.Date parsedDateMax = dateFormat.parse((String) filtre.get("dateMax"));

            // Convert java.util.Date to java.sql.Date
            Date dateMin = new java.sql.Date(parsedDateMin.getTime());
            Date dateMax = new java.sql.Date(parsedDateMax.getTime());

            
            List<Integer> cryptoIds = null;
            if (filtre.containsKey("cryptos")) {
                cryptoIds = ((List<?>) filtre.get("cryptos"))
                    .stream()
                    .map(item -> ((Number) item).intValue())
                    .collect(Collectors.toList());
            }

            // Récupération des cryptos selon les critères
            List<Crypto> cryptos;
            if (cryptoIds != null && !cryptoIds.isEmpty()) {
                cryptos = cryptoService.getCryptoBetweenDateAndList(dateMin, dateMax, cryptoIds);
            } else {
                cryptos = cryptoService.getCryptoBetweenDate(dateMin, dateMax);
            }

            // Calcul du résultat selon le type d'analyse
            Double result = null;
            switch (idType) {
                case 1:
                    result = cryptoService.calculerPremierQuartile(cryptos);
                    break;
                case 2:
                    result = cryptoService.calculerMax(cryptos);
                    break;
                case 3:
                    result = cryptoService.calculerMin(cryptos);
                    break;
                case 4:
                    result = cryptoService.calculerMoyenne(cryptos);
                    break;
                case 5:
                    result = cryptoService.calculerEcartType(cryptos);
                    break;
                default:
                    return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
