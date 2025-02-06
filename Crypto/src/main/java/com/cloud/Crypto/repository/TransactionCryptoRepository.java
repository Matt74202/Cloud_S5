package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.Crypto;
import model.Fond;
import model.TransactionCrypto;
import model.TransactionFond;
import model.TypeTransaction;
import model.User;

public class TransactionCryptoRepository {
     private final JdbcTemplate jdbcTemplate;

    public TransactionCryptoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public RowMapper<TransactionCrypto> getTransactionCryptoRowMapper(){
        return(rs,rowNum)->{
            TransactionCrypto fond=new TransactionCrypto();
            new User(rs.getInt("id_utilisateur"));
            fond.setDate(rs.getDate("date"));
            new TypeTransaction(rs.getInt("id_transaction"));
            fond.setMontant(rs.getDouble("montant"));
            new Fond(rs.getInt("id_"));
            fond.setEtat(rs.getString("etat"));

            return fond;

        };



    }

}
