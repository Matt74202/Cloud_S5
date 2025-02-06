package com.cloud.Crypto.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import model.Fond;
import model.User;

@Repository
public class FondRepository {
     private final JdbcTemplate jdbcTemplate;
     

    public FondRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<Fond> getFondRowMapper() {
        return (rs, rowNum) -> new Fond(
            rs.getInt("id"),
            new User(rs.getInt("id_utilisateur")),
            rs.getDouble("solde")
            
        );
    }

    public int update(double solde,int idUtilisateur){
        String sql="Update Fond set solde=? where id_utilisateur=?";
        return jdbcTemplate.update(sql, solde, idUtilisateur);
    }

    



}
