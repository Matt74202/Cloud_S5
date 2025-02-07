package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.UserRepository;
import model.Crypto;
import model.Fond;
import model.TransactionFond;
import model.TransactionCrypto;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void inscription(User user) {
        userRepository.Inscription(user);
    }

    public User login(String email, String mdp) {
        return userRepository.login(email, mdp);
    }

    public List<Crypto> getPortefeuille(int userId) {
        return userRepository.getPortefeuille(userId);
    }

    public Fond getFond(int userId) {
        return userRepository.getFond(userId);
    }

    public TransactionFond makeTransactionFond(int idUser, TransactionFond transaction) {
        return userRepository.makeTransactionFond(idUser, transaction);
    }

    public TransactionCrypto makeTransactionCrypto(int idUser, TransactionCrypto transaction) {
        return userRepository.makeTransactionCrypto(idUser, transaction);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }
}
