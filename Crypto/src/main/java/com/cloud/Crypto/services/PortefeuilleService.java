package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.*;
import model.Crypto;
import model.Fond;
import model.TransactionFond;
import model.TransactionCrypto;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortefeuilleService {

    private final PortefeuilleRepository portefeuilleRepository;

    public List<Portefeuille> getPortefeuille(int userId) {
        return portefeuilleRepository.getPortefeuille(userId);
    }

    public Portefeuille getPortefeuilleFiltre(int userId, int idCrypto){
        return portefeuilleRepository.getPortefeuilleFiltre(userId, idCrypto);
    }

}
