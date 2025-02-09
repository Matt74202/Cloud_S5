package model;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crypto implements FirestoreSyncable {
    int id;
    String nom;
    Double valeur;
    Timestamp date;

    public Crypto(){
        
    }

    public Crypto(int int1) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getFirestoreCollectionName() {
        return "Crypto";
    }

    @Override
    public Map<String, Object> toFirestoreMap() {
        Map<Crypto> map= new HashMap<>();
        
        return null;
    }
}
