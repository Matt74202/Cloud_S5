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



@Repository
public class CryptoRepository {
    private final JdbcTemplate jdbcTemplate;

    public CryptoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public RowMapper<Crypto> getCryptoRowMapper(){
        return(rs,rowNum)->{
            Crypto crypto=new Crypto();
            crypto.setId(rs.getInt("id"));
            crypto.setNom(rs.getString("nom"));
            crypto.setValeur(rs.getDouble("valeur"));
            crypto.setDate(rs.getDate("date"));
            return crypto;

        };

        

    }

    public void generateAndInsertCryptos() {
        String sql = "INSERT INTO Crypto (nom, valeur, date) VALUES (?, ?, ?)";
        Random random = new Random();
        String[] cryptoNames = {"Bitcoin", "Ethereum", "Solana", "Cardano", "Polkadot", 
                                "Ripple", "Dogecoin", "Litecoin", "Chainlink", "Avalanche"};

        for (String name : cryptoNames) {
            BigDecimal value = BigDecimal.valueOf(100 + (1000 - 100) * random.nextDouble()); // Random value between 100 and 1000
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

            jdbcTemplate.update(sql, name, value, timestamp);
        }
    }

    // Retrieve all cryptos using RowMapper
    public List<Crypto> getAllCryptos() {
        String sql = "SELECT * FROM Crypto";
        return jdbcTemplate.query(sql, getCryptoRowMapper());
    }

    public List<Crypto> getAllLatestCryptos() {
        String sql = "SELECT * FROM Crypto WHERE date = (SELECT MAX(date) FROM Crypto c2 WHERE c1.nom = c2.nom)";
        return jdbcTemplate.query(sql, getCryptoRowMapper());
    }

    public Crypto getById(int id) {
        String sql = "SELECT * FROM Crypto WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getCryptoRowMapper(), id);
    }

    


    




    




    
}
