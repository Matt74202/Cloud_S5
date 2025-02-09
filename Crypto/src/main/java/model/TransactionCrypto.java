package model;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class TransactionCrypto implements FirestoreSyncable{
    public TransactionCrypto() {
        //TODO Auto-generated constructor stub
    }
    int id;
    User user;
    Date date;
    TypeTransaction type;
    double quantite;
    Crypto crypto;
    String etat;

    public TransactionCrypto(Date date, int idTypeTransaction, int idCrypto, double quantite) {
        this.date = date;
        this.type = new TypeTransaction(idTypeTransaction, "");
        this.quantite = quantite;
        this.crypto = new Crypto(); 
        this.crypto.setId(idCrypto); 
        this.etat = "en attente";
    }

    @Override
    public String getFirestoreCollectionName() {
        // TODO Auto-generated method stub
        return "MouvementCrypto";
    }

    @Override
    public Map<String, Object> toFirestoreMap() {
        Map<String, Object> map= new HashMap<>();
        if (this.getId() != 0) map.put("id",this.id);
        if (user != null) map.put("user", user);
        if (date != null) map.put("date", date);
        if (type != null) map.put("type", type);
        if (quantite != 0) map.put("quantite", quantite);
        if (crypto != null) map.put("crypto", crypto);
        if (etat != null) map.put("etat", etat);
        return map;
       
    }
    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return this.id;
    }

}
