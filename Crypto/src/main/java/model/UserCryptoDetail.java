package model;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCryptoDetail {

    User user;
    double totalAchat;
    double totalVente;
    double valeurPortefeuille;

}
