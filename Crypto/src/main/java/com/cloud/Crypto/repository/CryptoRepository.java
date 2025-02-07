package com.cloud.Crypto.repository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import model.*;

import java.util.ArrayList;



@Repository
public class CryptoRepository {
    private final JdbcTemplate jdbcTemplate;

    public CryptoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<Crypto> getCryptoRowMapper() {
        return (rs, rowNum) -> {
            Crypto crypto = new Crypto();
            crypto.setId(rs.getInt("id"));
            crypto.setNom(rs.getString("nom"));
            crypto.setValeur(rs.getDouble("valeur"));
            crypto.setDate(rs.getTimestamp("date"));
            return crypto;
        };
    }
    

    // public void generateAndInsertCryptos() {
    //     String sql = "INSERT INTO Crypto (nom, valeur, date) VALUES (?, ?, ?)";
    //     Random random = new Random();
    //     String[] cryptoNames = {"Bitcoin", "Ethereum", "Solana", "Cardano", "Polkadot", 
    //                             "Ripple", "Dogecoin", "Litecoin", "Chainlink", "Avalanche"};

    //     for (String name : cryptoNames) {
    //         BigDecimal value = BigDecimal.valueOf(100 + (1000 - 100) * random.nextDouble()); // Random value between 100 and 1000
    //         Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

    //         jdbcTemplate.update(sql, name, value, timestamp);
    //     }
    // }
    public List<Crypto> generateAndInsertCryptos() {
        String sqlInsert = "INSERT INTO ValeurCrypto (id_crypto, valeur, date) VALUES (?, ?, ?)"; 
        List<Crypto> latestCryptos = getCryptoLast(); 
        List<Crypto> updatedCryptos = new ArrayList<>();  
    
        Random random = new Random();
    
        for (Crypto crypto : latestCryptos) {
            double lastValue = crypto.getValeur(); 
            double variationPercentage = 0.1 * (random.nextDouble() * 2 - 1);  // random + ou - 10% de la dernière valeur
            double newValue = lastValue * (1 + variationPercentage); 
    
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

            jdbcTemplate.update(sqlInsert, crypto.getId(), newValue, timestamp); 
    
            Crypto updatedCrypto = new Crypto();
            updatedCrypto.setId(crypto.getId());
            updatedCrypto.setNom(crypto.getNom());
            updatedCrypto.setValeur(newValue);
            updatedCrypto.setDate(timestamp); 
    
            updatedCryptos.add(updatedCrypto); 
        }
    
        return updatedCryptos;  
    }
    

    public List<Crypto> getCryptoLast() {
        String sql = "SELECT * FROM v_crypto_last";
        return jdbcTemplate.query(sql, getCryptoRowMapper());
    }

    public Crypto getById(int id) {
        String sql = "SELECT * FROM v_crypto_last WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getCryptoRowMapper(), id);
    }

    public List<Crypto> getHistorique (int id) {
        String sql = "SELECT * FROM v_crypto_historique WHERE id=?";
        return jdbcTemplate.query(sql, getCryptoRowMapper(), id);
    }
    

    


    




    




    
}
