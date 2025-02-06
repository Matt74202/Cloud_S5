package model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fond {
    public Fond(int int1) {
        //TODO Auto-generated constructor stub
    }
    int id;
    User user;
    Double solde;
}
