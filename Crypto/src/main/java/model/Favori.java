package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favori {
    int id;
    User user;
    Crypto crypto;

    public Favori(int idCrypto){
        Crypto crypto= new Crypto();
        crypto.setId(idCrypto);
        this.setCrypto(crypto);
    }
}
