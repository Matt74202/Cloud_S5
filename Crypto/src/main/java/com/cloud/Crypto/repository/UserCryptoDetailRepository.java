package com.cloud.Crypto.repository;

import model.UserCryptoDetail;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserCryptoDetailRepository {

    private final JdbcTemplate jdbcTemplate; 
    private final UserRepository userRepository;


    private RowMapper<UserCryptoDetail> getRowMapper() {
        return (rs, rowNum) -> {
            UserCryptoDetail ucd = new UserCryptoDetail();
            User user = userRepository.getById(rs.getInt("utilisateur_id"));
            ucd.setUser(user);
            ucd.setTotalAchat(rs.getDouble("total_achat"));
            ucd.setTotalVente(rs.getDouble("total_vente"));
            ucd.setValeurPortefeuille(rs.getDouble("valeur_portefeuille"));
            return ucd;
        };
    }

    public List<UserCryptoDetail> getAll() {
        String sql = "SELECT * FROM v_user_transaction_summary";  
        return jdbcTemplate.query(sql, getRowMapper());
    }
}
