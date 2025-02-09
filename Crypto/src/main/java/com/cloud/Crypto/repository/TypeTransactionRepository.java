package com.cloud.Crypto.repository;

import model.TypeTransaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeTransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    private RowMapper<TypeTransaction> getRowMapper() {
        return (rs, rowNum) -> new TypeTransaction(
            rs.getInt("id"),
            rs.getString("type")
        );
    }

    public TypeTransaction getById(int id) {
        String sql = "SELECT * FROM TypeTransaction WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getRowMapper(), id);
    }

    public List<TypeTransaction> getAll() {
        String sql = "SELECT * FROM TypeTransaction";  
        return jdbcTemplate.query(sql, getRowMapper());
    }

}
