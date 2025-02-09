package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.UserCryptoDetailRepository;
import model.UserCryptoDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCryptoDetailService {

    private final UserCryptoDetailRepository userCryptoDetailRepository;

    public UserCryptoDetailService(UserCryptoDetailRepository userCryptoDetailRepository) {
        this.userCryptoDetailRepository = userCryptoDetailRepository;
    }

    public List<UserCryptoDetail> getAllUserCryptoDetails() {
        return userCryptoDetailRepository.getAll();
    }

}
