package com.cloud.Crypto.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Crypto;
import model.TransactionFond;
import model.TypeTransaction;
import model.User;

@Repository
public class TransactionFondRepository {
    private final JdbcTemplate jdbcTemplate;

    public TransactionFondRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public RowMapper<TransactionFond> getTransactionFondRowMapper(){
        return(rs,rowNum)->{
            TransactionFond fond=new TransactionFond();
            new User(rs.getInt("id_utilisateur"));
            fond.setDate(rs.getDate("date"));
            new TypeTransaction(rs.getInt("id_transaction"));
            fond.setMontant(rs.getDouble("montant"));
            new Crypto(rs.getInt("id_crypto"));
            fond.setEtat(rs.getString("etat"));

            return fond;

        };



    }

}
