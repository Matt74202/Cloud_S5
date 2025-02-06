package model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Portefeuille {
    public Portefeuille() {
        //TODO Auto-generated constructor stub
    }
    int id;
    User user;
    Double solde;
    Crypto crypto;
}
