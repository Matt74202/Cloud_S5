package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;

@Data
@AllArgsConstructor
public class Commission {
    int id;
    double pourcentageVente;
    double pourcentageAchat;
    Date date;
}
