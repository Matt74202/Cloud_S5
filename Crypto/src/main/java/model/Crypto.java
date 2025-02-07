package model;
import java.sql.Timestamp;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crypto {
    int id;
    String nom;
    Double valeur;
    Timestamp date;

    public Crypto(){
        
    }

    public Crypto(int int1) {
        //TODO Auto-generated constructor stub
    }
}
