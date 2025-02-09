package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.UserRepository;
import com.cloud.Crypto.repository.FavoriRepository;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavoriRepository favoriRepository;
    private final FirestoreService firebaseService; 

    public void inscription(User user) {
        userRepository.Inscription(user);
    }

    public User login(String email, String mdp) {
        return userRepository.login(email, mdp);
    }

    public Fond getFond(int userId) {
        return userRepository.getFond(userId);
    }

    public TransactionFond makeTransactionFond(int idUser, TransactionFond transaction) {
        firebaseService.syncToFirestore(transaction);
        return userRepository.makeTransactionFond(idUser, transaction);
    }

    public TransactionCrypto makeTransactionCrypto(int idUser, TransactionCrypto transaction) {
        firebaseService.syncToFirestore(transaction);
        return userRepository.makeTransactionCrypto(idUser, transaction);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public double getValeurPortefeuille(int idUser){
        return userRepository.getValeurPortefeuille(idUser);
    }

    public User changerPdp(int idUser, String image){
        return userRepository.changerPdp(idUser, image);
    }
}
