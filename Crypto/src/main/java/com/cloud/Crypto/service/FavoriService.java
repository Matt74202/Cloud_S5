package com.cloud.Crypto.service;

import com.cloud.Crypto.repository.FavoriRepository;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriService {

    private final FavoriRepository favoriRepository;

    public Favori insertFavori(int idUser, int idCrypto){
        return favoriRepository.insertFavori(idUser, idCrypto);
    }

    public List<Favori> getAllFavori(int idUser){
        return favoriRepository.getAllFavori(idUser);
    }

}
