package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import model.Crypto;
import model.Fond;
import model.TransactionCrypto;
import model.TransactionFond;
import model.TypeTransaction;
import model.User;

import java.util.List;
import java.sql.Date;

@Repository
@AllArgsConstructor
public class TransactionCryptoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final TypeTransactionRepository typeTransactionRepository;
    private final CryptoRepository cryptoRepository;

    
    public RowMapper<TransactionCrypto> getTransactionCryptoRowMapper(){
        return(rs, rowNum) -> {
            TransactionCrypto crypto = new TransactionCrypto();
            User user = userRepository.getById(rs.getInt("id_utilisateur"));
            crypto.setUser(user);
            crypto.setDate(rs.getDate("date"));
            TypeTransaction type = typeTransactionRepository.getById(rs.getInt("id_type"));
            crypto.setType(type);
            Crypto c= cryptoRepository.getById(rs.getInt("id_crypto"));
            crypto.setCrypto(c);
            crypto.setQuantite(rs.getDouble("quantite"));
            crypto.setEtat(rs.getString("etat"));

            return crypto;
        };



    }

    public List<TransactionCrypto> getAll() {
        String sql = "SELECT * FROM MouvementCrypto";  
        return jdbcTemplate.query(sql, getTransactionCryptoRowMapper());
    }
    
    public List<TransactionCrypto> getAllFiltre(Date dateMax) {
        String sql = "SELECT * FROM MouvementCrypto WHERE date <= ?";
        return jdbcTemplate.query(sql, getTransactionCryptoRowMapper(), dateMax);
    }

    public List<TransactionCrypto> getAllByUser(int idUser) {
        String sql = "SELECT * FROM MouvementCrypto WHERE id_utilisateur = ?";
        return jdbcTemplate.query(sql, getTransactionCryptoRowMapper(), idUser);
    }

    public List<TransactionCrypto> getAllFiltre(Date dateMax, Integer idUser, Integer idCrypto) {
        StringBuilder sql = new StringBuilder("SELECT * FROM MouvementCrypto WHERE 1=1");
    
        if (idCrypto != null && idCrypto > 0) {
            sql.append(" AND id_crypto = ").append(idCrypto);
        }
        if (dateMax!=null) {
            sql.append(" AND date<= ").append(dateMax);
        }
        if (idUser != null && idUser > 0) {
            sql.append(" AND id_utilisateur = ").append(idUser);
        }
    
        return jdbcTemplate.query(sql.toString(), getTransactionCryptoRowMapper());
    }

    public List<TransactionCrypto> getAllFiltre(Integer idCrypto, Integer idType, Integer idUtilisateur) {
        StringBuilder sql = new StringBuilder("SELECT * FROM MouvementCrypto WHERE 1=1");
    
        if (idCrypto != null && idCrypto > 0) {
            sql.append(" AND id_crypto = ").append(idCrypto);
        }
        if (idType != null && idType > 0) {
            sql.append(" AND id_type = ").append(idType);
        }
        if (idUtilisateur != null && idUtilisateur > 0) {
            sql.append(" AND id_utilisateur = ").append(idUtilisateur);
        }
    
        return jdbcTemplate.query(sql.toString(), getTransactionCryptoRowMapper());
    }
    
}
