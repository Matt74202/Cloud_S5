package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.AdminRepository;
import lombok.AllArgsConstructor;
import model.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;


}
