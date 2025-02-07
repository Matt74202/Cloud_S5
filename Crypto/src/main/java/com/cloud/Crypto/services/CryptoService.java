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

    public void generateAndInsertCryptos() {
        cryptoRepository.generateAndInsertCryptos();
    }

    public List<Crypto> getAllCryptos() {
        return cryptoRepository.getAllCryptos();
    }

    public List<Crypto> getAllLatestCryptos() {
        return cryptoRepository.getAllLatestCryptos();
    }

    public Crypto getCryptoById(int id) {
        return cryptoRepository.getById(id);
    }
}
