package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int id;
    String nom;
    String email;
    String mdp;
    String role;
    String pdp;

    public User(){} 
    
    public User(int id ){
        this.id=id;
    }

    public User(String email, String mdp){
        this.email= email;
        this.mdp= mdp;
    }
}
