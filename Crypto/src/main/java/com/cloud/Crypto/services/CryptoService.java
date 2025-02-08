package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.CryptoRepository;
import model.Crypto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Date;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public List<Crypto> generateAndInsertCryptos() {
        return cryptoRepository.generateAndInsertCryptos();
    }

    public List<Crypto> getCryptoLast() {
        return cryptoRepository.getCryptoLast();
    }

    public Crypto getCryptoById(int id) {
        return cryptoRepository.getById(id);
    }

    public List<Crypto> getHistorique(int id) {
        return cryptoRepository.getHistorique(id);
    }

    public List<Crypto> getCryptoBetweenDateAndList(Date dateMin, Date dateMax, List<Integer> cryptoIds) {
        return cryptoRepository.getCryptoBetweenDateAndList(dateMin, dateMax, cryptoIds);
    }

    public List<Crypto> getCryptoBetweenDate(Date dateMin, Date dateMax) {
        return cryptoRepository.getCryptoBetweenDate(dateMin, dateMax);
    }

    public Double calculerPremierQuartile(List<Crypto> cryptos) {
        // Trier les cryptos par valeur
        cryptos.sort((c1, c2) -> Double.compare(c1.getValeur(), c2.getValeur()));

        int n = cryptos.size();
        if (n == 0) return null;
        
        // Indice pour le premier quartile
        int index = (int) Math.floor(0.25 * n);
        
        // Si l'indice est supérieur à zéro, retourner la valeur du premier quartile
        if (index < n) {
            return cryptos.get(index).getValeur();
        } else {
            return cryptos.get(n - 1).getValeur();
        }
    }

    public Double calculerMoyenne(List<Crypto> cryptos) {
        return cryptos.stream()
                      .mapToDouble(Crypto::getValeur)
                      .average()
                      .orElse(Double.NaN); // Return NaN if the list is empty
    }
    
    public Double calculerMin(List<Crypto> cryptos) {
        return cryptos.stream()
                      .mapToDouble(Crypto::getValeur)
                      .min()
                      .orElse(Double.NaN); // Return NaN if no value exists
    }
    
    public Double calculerMax(List<Crypto> cryptos) {
        return cryptos.stream()
                      .mapToDouble(Crypto::getValeur)
                      .max()
                      .orElse(Double.NaN); // Return NaN if no value exists
    }
    
    public Double calculerEcartType(List<Crypto> cryptos) {
        double moyenne = calculerMoyenne(cryptos);
        double variance = cryptos.stream()
                                 .mapToDouble(c -> Math.pow(c.getValeur() - moyenne, 2))
                                 .average()
                                 .orElse(0.0); // If no data, return 0 for variance
        return Math.sqrt(variance);
    }
    
}
