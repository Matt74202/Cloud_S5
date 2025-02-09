package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import model.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class AdminRepository {
     private final JdbcTemplate jdbcTemplate;


    
    private RowMapper<Admin> getAdminRowMapper() {
        return (rs, rowNum) -> new Admin(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("mdp")
        );
        
    }

    
    
}