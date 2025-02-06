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
    double montant;
    Crypto crypto;
    String etat;
}
