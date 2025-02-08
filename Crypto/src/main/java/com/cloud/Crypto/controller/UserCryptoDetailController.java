package com.cloud.Crypto.controller;

import com.cloud.Crypto.service.UserCryptoDetailService;
import model.UserCryptoDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/crypto/detail")
public class UserCryptoDetailController {

    private final UserCryptoDetailService userCryptoDetailService;

    public UserCryptoDetailController(UserCryptoDetailService userCryptoDetailService) {
        this.userCryptoDetailService = userCryptoDetailService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserCryptoDetail>> getAllUserCryptoDetails() {
        List<UserCryptoDetail> userCryptoDetails = userCryptoDetailService.getAllUserCryptoDetails();
        return ResponseEntity.ok(userCryptoDetails);
    }
}
