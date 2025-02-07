package model;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionCrypto {
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
}
