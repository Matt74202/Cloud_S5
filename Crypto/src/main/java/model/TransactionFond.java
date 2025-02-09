package model;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFond implements FirestoreSyncable {

    int id;
    User user;
    Date date;
    TypeTransaction type;
    double montant;
    String etat="en attente";

    public TransactionFond(Date date, int idTypeTransaction, double montant) {
        this.date = date;
        this.type = new TypeTransaction(idTypeTransaction, "");  
        this.montant = montant;
        this.etat = "en attente";  // Exemple d'état par défaut, à ajuster selon votre logique
    }

    
    @Override
    public String getFirestoreCollectionName() {
        // TODO Auto-generated method stub
        return "MouvementFond";
    }

    @Override
    public Map<String, Object> toFirestoreMap() {
        Map<String, Object> map= new HashMap<>();
        if (this.getId() != 0) map.put("id",this.id);
        if (user != null) map.put("user", user);
        if (date != null) map.put("date", date);
        if (type != null) map.put("type", type);
        if (montant != 0) map.put("montant", montant);
        if (etat != null) map.put("etat", etat);
        return map;
       
    }
    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return this.id;
    }
}
