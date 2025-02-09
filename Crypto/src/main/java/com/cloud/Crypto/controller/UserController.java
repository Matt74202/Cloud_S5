package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.*;
import com.cloud.Crypto.dto.*;
import model.Crypto;
import model.Fond;
import model.TransactionFond;
import model.TransactionCrypto;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.sql.Date;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PortefeuilleService portefeuilleService;
    private final CryptoService cryptoService;
    private final FavoriService favoriService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(){
        List<User> users= userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/inscription")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        userService.inscription(user);
        //ApiResponse response= new ApiResponse("success", "Utilisateur cree avec succes", null);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest userRequest) {
        User user = userService.login(userRequest.getMail(), userRequest.getMdp());
        
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body(null); 
        }
    }

    @GetMapping("/{userId}/portefeuille")
    public ResponseEntity<List<Portefeuille>> getUserPortefeuille(@PathVariable int userId) {
        List<Portefeuille> portefeuille = portefeuilleService.getPortefeuille(userId);
        return ResponseEntity.ok(portefeuille);
    }

    @GetMapping("/{userId}/portefeuille/{idCrypto}")
    public ResponseEntity<Portefeuille> getUserPortefeuilleFiltre(@PathVariable int userId, @PathVariable int idCrypto) {
        Portefeuille portefeuille = portefeuilleService.getPortefeuilleFiltre(userId, idCrypto);
        return ResponseEntity.ok(portefeuille);
    }

    @GetMapping("/{userId}/portefeuille/valeur")
    public ResponseEntity<Double> getUserPortefeuilleValeur(@PathVariable int userId) {
        Double valeur = userService.getValeurPortefeuille(userId);
        return ResponseEntity.ok(valeur);
    }

    @GetMapping("/{userId}/fond")
    public ResponseEntity<Fond> getUserFond(@PathVariable int userId) {
        Fond fond = userService.getFond(userId);
        return ResponseEntity.ok(fond);
    }

    @PostMapping("/{userId}/transaction/fond")
    public ResponseEntity<?> makeTransactionFond(@PathVariable int userId, @RequestBody Map<String, Object> payload) {
        int idTypeTransaction = (int) payload.get("idType");
        Date date = Date.valueOf((String) payload.get("date"));  
        double montant = ((Number) payload.get("montant")).doubleValue();

        Fond fond = userService.getFond(userId);
        double soldeFond = fond.getSolde(); 

        if (idTypeTransaction == 2 && montant > soldeFond) {
            return ResponseEntity.status(400).body("Solde insuffisant pour effectuer cette transaction.");
        }

        TransactionFond transaction = new TransactionFond(date, idTypeTransaction, montant);
        TransactionFond newTransaction = userService.makeTransactionFond(userId, transaction);

        return ResponseEntity.ok(newTransaction);
    }

    @PostMapping("/{userId}/transaction/crypto")
    public ResponseEntity<?> makeTransactionCrypto(@PathVariable int userId, @RequestBody Map<String, Object> payload) {
        int idTypeTransaction = (int) payload.get("idType");
        Date date = Date.valueOf((String) payload.get("date"));
        int idCrypto = (int) payload.get("idCrypto");
        double quantite = ((Number) payload.get("quantite")).doubleValue();

        if (idTypeTransaction == 1) { // Achat
            Crypto cryptoValeur= cryptoService.getCryptoById(idCrypto);
            double totalCost = cryptoValeur.getValeur() * quantite;
                
            Fond fond = userService.getFond(userId);
            if (fond.getSolde() < totalCost) {
                return ResponseEntity.status(400).body("Solde insuffisant pour effectuer cette transaction.");
            }
        } 

        else if (idTypeTransaction == 2) { // Vente
        Portefeuille portefeuille= portefeuilleService.getPortefeuilleFiltre(userId, idCrypto);
                
            if (portefeuille == null || portefeuille.getSolde() < quantite) {
                return ResponseEntity.status(400).body("Crypto insuffisant pour effectuer cette transaction.");
            } 
        }

        TransactionCrypto transaction= new TransactionCrypto(date, idTypeTransaction, idCrypto, quantite);
        TransactionCrypto newTransaction = userService.makeTransactionCrypto(userId, transaction);
            
        return ResponseEntity.ok(newTransaction);
    }

    @PostMapping("/{userId}/favori")
    public ResponseEntity<Favori> insertFavori(@PathVariable int userId, @RequestBody int idCrypto) {
        Favori favori= favoriService.insertFavori(userId, idCrypto);
        return ResponseEntity.ok(favori);
    }

    @GetMapping("/{userId}/favori")
    public ResponseEntity<List<Favori>> getAllFavori(@PathVariable int userId) {
        List<Favori> favoris= favoriService.getAllFavori(userId);
        return ResponseEntity.ok(favoris);
    }

    @PostMapping("/{userId}/pdp")
    public ResponseEntity<User> changerPdp(@PathVariable int userId, @RequestBody String image) {
        User user= userService.changerPdp(userId, image);
        return ResponseEntity.ok(user);
    }


}
