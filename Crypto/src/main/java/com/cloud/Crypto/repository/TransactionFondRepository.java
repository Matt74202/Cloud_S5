package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import model.Crypto;
import model.TransactionFond;
import model.TypeTransaction;
import model.User;

import java.sql.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class TransactionFondRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final TypeTransactionRepository typeTransactionRepository;
    private final CryptoRepository cryptoRepository;
    
    public RowMapper<TransactionFond> getTransactionFondRowMapper(){
        return(rs, rowNum) -> {
            TransactionFond fond = new TransactionFond();
            User user = userRepository.getById(rs.getInt("id_utilisateur"));
            fond.setUser(user);
            fond.setDate(rs.getDate("date"));
            TypeTransaction type = typeTransactionRepository.getById(rs.getInt("id_type"));
            fond.setType(type);
            fond.setMontant(rs.getDouble("montant"));
            fond.setEtat(rs.getString("etat"));

            return fond;
        };
    }

    public List<TransactionFond> getAll() {
        String sql = "SELECT * FROM MouvementFond";  
        return jdbcTemplate.query(sql, getTransactionFondRowMapper());
    }
    
    public List<TransactionFond> getAllFiltre(Date dateMax) {
        String sql = "SELECT * FROM MouvementFond WHERE date <= ?";
        return jdbcTemplate.query(sql, getTransactionFondRowMapper(), dateMax);
    }

    public List<TransactionFond> getAllNonValides() {
        String sql = "SELECT * FROM MouvementFond WHERE etat='en attente'";  
        return jdbcTemplate.query(sql, getTransactionFondRowMapper());
    }

    public TransactionFond getById(int id) {
        String sql = "SELECT * FROM MouvementFond WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getTransactionFondRowMapper(), id);
    }

    public TransactionFond valider(int idTransaction) {
        String updateQuery = "UPDATE MouvementFond SET etat = 'valide' WHERE id = ?";
        jdbcTemplate.update(updateQuery, idTransaction);
        TransactionFond transaction= getById(idTransaction);
        transaction.setEtat("valide");
        return transaction;
    }
    
}
