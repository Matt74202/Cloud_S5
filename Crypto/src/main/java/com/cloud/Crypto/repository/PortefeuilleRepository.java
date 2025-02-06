package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.Crypto;
import model.Fond;
import model.Portefeuille;
import model.User;

public class PortefeuilleRepository {
    private final JdbcTemplate jdbcTemplate;
     

    public PortefeuilleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<Portefeuille> getFondRowMapper() {
        return (rs, rowNum) ->{
            Portefeuille port=new Portefeuille();
            port.setId(rs.getInt("id"));
            new User(rs.getInt("id_utilisateur"));
            port.setSolde(rs.getDouble("solde"));
            new Crypto(rs.getInt("id_crypto"));
            return port;
        };
           
    }

    public int update(double solde,int idUtilisateur){
        String sql="Update Portefeuille set solde=? where id_utilisateur=?";
        return jdbcTemplate.update(sql, solde, idUtilisateur);
    }
}
