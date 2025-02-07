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
import model.ApiResponse;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PortefeuilleService portefeuilleService;


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
    public ResponseEntity<TransactionFond> makeTransactionFond(@PathVariable int userId, @RequestBody TransactionFond transaction) {
        TransactionFond newTransaction = userService.makeTransactionFond(userId, transaction);
        return ResponseEntity.ok(newTransaction);
    }

    @PostMapping("/{userId}/transaction/crypto")
    public ResponseEntity<TransactionCrypto> makeTransactionCrypto(@PathVariable int userId, @RequestBody TransactionCrypto transaction) {
        TransactionCrypto newTransaction = userService.makeTransactionCrypto(userId, transaction);
        return ResponseEntity.ok(newTransaction);
    }
}
