package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import model.Crypto;
import model.Fond;
import model.Portefeuille;
import model.User;

import java.util.List;

@AllArgsConstructor
@Repository
public class PortefeuilleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;
     

    public RowMapper<Portefeuille> getPortefeuilleRowMapper() {
        return (rs, rowNum) ->{
            Portefeuille port=new Portefeuille();
            port.setId(rs.getInt("id"));
            User user= userRepository.getById(rs.getInt("id_utilisateur"));
            port.setUser(user);
            port.setSolde(rs.getDouble("solde"));
            Crypto crypto= cryptoRepository.getById(rs.getInt("id_crypto"));
            port.setCrypto(crypto);
            return port;
        };
           
    }

    public int update(double solde,int idUtilisateur){
        String sql="Update Portefeuille set solde=? where id_utilisateur=?";
        return jdbcTemplate.update(sql, solde, idUtilisateur);
    }

    public List<Portefeuille> getPortefeuille(int userId) {
        String sql = "SELECT* FROM Portefeuille WHERE id_utilisateur=?";
        return jdbcTemplate.query(sql, getPortefeuilleRowMapper(), userId);
    }

    public Portefeuille getPortefeuilleFiltre(int userId, int idCrypto){
        String sql = "SELECT* FROM Portefeuille WHERE id_utilisateur=? AND id_crypto=?";
        return jdbcTemplate.queryForObject(sql, getPortefeuilleRowMapper(), new Object[]{userId, idCrypto});
    }


}
