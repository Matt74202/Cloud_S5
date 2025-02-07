package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.CryptoRepository;
import model.Crypto;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
