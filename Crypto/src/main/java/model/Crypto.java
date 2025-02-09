package model;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crypto implements FirestoreSyncable{
    int id;
    String nom;
    Double valeur;
    Timestamp date;



    public Crypto(int int1) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getFirestoreCollectionName() {
        return "Crypto";
    }
    

    @Override
    public Map<String, Object> toFirestoreMap() {
        Map<String, Object> map= new HashMap<>();
        if (id!= 0) map.put("id", id);
        if (nom != null) map.put("nom", nom);
        if (valeur != null) map.put("valeur", valeur);
        if (date != null) map.put("date", date);
        return map;
    }
}
