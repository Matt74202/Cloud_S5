package com.cloud.Crypto.repository;

import model.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class CommissionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Commission insert(Commission commission) {
        String sql = "INSERT INTO Commission (pourcentage_vente, pourcentage_achat, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, commission.getPourcentageVente(), commission.getPourcentageAchat(), new java.sql.Date(commission.getDate().getTime()));
        return commission;
    }

    private RowMapper<Commission> getRowMapper() {
        return (rs, rowNum) -> new Commission(
            rs.getInt("id"),
            rs.getDouble("pourcentage_vente"),
            rs.getDouble("pourcentage_achat"),
            rs.getDate("date")            
        );
    }

    public List<Commission> getAll() {
        String sql = "SELECT * FROM Commission";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    public List<Commission> getAllByDate(Date dateMax) {
        String sql = "SELECT * FROM Commission WHERE date <= ?";
        return jdbcTemplate.query(sql, getRowMapper(), new java.sql.Date(dateMax.getTime()));
    }
}
