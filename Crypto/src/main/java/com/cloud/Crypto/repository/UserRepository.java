package com.cloud.Crypto.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import model.*;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;


@Repository
public class UserRepository {
     private final JdbcTemplate jdbcTemplate;
     private final CryptoRepository cryptoRepository;
     private final FondRepository fondRepository;
     private final TransactionFondRepository transactionFondRepository;
     private final TransactionCryptoRepository transactionCryptoRepository;

    public UserRepository(CryptoRepository cryptoRepository,FondRepository fondRepository,TransactionFondRepository transactionFondRepository,TransactionCryptoRepository transactionCryptoRepository,JdbcTemplate jdbcTemplate) {
        this.cryptoRepository = cryptoRepository;
        this.fondRepository = fondRepository;
        this.transactionFondRepository=transactionFondRepository;
        this.transactionCryptoRepository=transactionCryptoRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private RowMapper<User> getUserRowMapper() {
        return (rs, rowNum) -> new User(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("email"),
            null, 
            null  
        );
        
    }


    public void Inscription(User user) {
        String sql = "INSERT INTO Utilisateur (nom, email, mdp) VALUES (?, ?, ?)"; 
        jdbcTemplate.update(sql, user.getNom(), user.getMail(), user.getMdp());
        System.out.println("User registered successfully.");
    }

    // public User login(String email, String mdp) {
    //     String sql = "SELECT * FROM Utilisateur WHERE email = ? AND mdp=?";

    //     try {
    //         User user = jdbcTemplate.queryForObject(sql, getUserRowMapper(), email);

    //         if (user != null && BCrypt.checkpw(mdp, getStoredPassword(email))) {
    //             return user;
    //         }
    //     } catch (Exception e) {
    //         return null; 
    //     }
    //     return null;
    // }

    // private String getStoredPassword(String email) {
    //     String sql = "SELECT mdp FROM Utilisateur WHERE email = ?";
    //     try {
    //         return jdbcTemplate.queryForObject(sql, String.class, email);
    //     } catch (Exception e) {
    //         return null;
    //     }
    // }

    public User login(String email, String mdp) {
        String sql = "SELECT * FROM Utilisateur WHERE email = ? AND mdp = ?";
        User user = jdbcTemplate.queryForObject(sql, getUserRowMapper(), new Object[]{email, mdp});
        return user;
    }
    
    public List<Crypto> getPortefeuille(int userId) {
        String sql = "SELECT c.id, c.nom, c.valeur, c.date, p.quantite " +
                     "FROM Portefeuille p " +
                     "JOIN Crypto c ON p.idCrypto = c.id " +
                     "WHERE p.id_utilisateur = ?";

        return jdbcTemplate.query(sql, cryptoRepository.getCryptoRowMapper(), userId);
    }

    public Fond getFond(int idUser){
        String sql="SELECT *from Fond where id_utilisateur=?";
        return jdbcTemplate.queryForObject(sql,fondRepository.getFondRowMapper(),idUser);
    }

    public TransactionFond makeTransactionFond(int idUser, TransactionFond transaction) {
        String sql = "INSERT INTO MouvementFond (id_user, date, type, montant, etat) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, idUser, transaction.getDate(), transaction.getType().getId(), transaction.getMontant(), transaction.getEtat());
        return transaction; 
    }

    public TransactionCrypto makeTransactionCrypto(int idUser, TransactionCrypto transaction) {
        String sql = "INSERT INTO MouvementCrypto (id_user, date, type, montant, id_crypto, etat) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, idUser, transaction.getDate(), transaction.getType(), transaction.getMontant(), transaction.getCrypto().getId(), transaction.getEtat());
        return transaction;
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM Utilisateur";  
        return jdbcTemplate.query(sql, getUserRowMapper());
    }
    



}
