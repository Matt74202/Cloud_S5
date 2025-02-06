package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int id;
    String nom;
    String mail;
    String mdp;
    String role;

    public User(int id ){
        this.id=id;
    }
}
