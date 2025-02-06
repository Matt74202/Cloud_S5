package model;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TransactionFond {
    public TransactionFond() {
        //TODO Auto-generated constructor stub
    }
    int id;
    User user;
    Date date;
    TypeTransaction type;
    double montant;
    Fond fond;
    String etat;
}
