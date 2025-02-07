package model;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFond {

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
}
