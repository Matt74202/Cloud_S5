package com.cloud.Crypto.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import model.*;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@AllArgsConstructor
@Repository
public class FavoriRepository {
     private final JdbcTemplate jdbcTemplate;
     private final UserRepository userRepository;
     private final CryptoRepository cryptoRepository;

    
    private RowMapper<Favori> getRowMapper() {
        return (rs, rowNum) -> {
            Favori favori= new Favori(); 
            User user= userRepository.getById(rs.getInt("id_utilisateur"));
            favori.setUser(user);
            Crypto crypto= cryptoRepository.getById(rs.getInt("id_crypto"));
            return favori;
        };
        
    }

    public List<Favori> getAllFavori(int idUser) {
        String sql = "SELECT * FROM Favori WHERE id_utilisateur=?";  
        return jdbcTemplate.query(sql, getRowMapper(), idUser);
    }

    public Favori insertFavori (int idUser, int idCrypto){
        String sql = "INSERT INTO Favori (id_utilisateur, id_crypto) VALUES (?, ?)";
        Favori favori= new Favori();
        User user= userRepository.getById(idUser);
        favori.setUser(user);
        Crypto crypto= cryptoRepository.getById(idCrypto);
        favori.setCrypto(crypto);
        jdbcTemplate.update(sql, idUser, favori.getCrypto());
        return favori;
    }

    


}
