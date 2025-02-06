package model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inscription {
    int id ;
    String nom;
    String prenom;
    String mdp;
   

}
