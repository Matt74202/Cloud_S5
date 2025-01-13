package model;

public class User {
    int id;
    String nom;
    String mail;
    String mdp;
    Role role;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public User(){
    }

    public User(int id, String nom, String mail, String mdp, Role role){
        this.setId(id);
        this.setNom(nom);
        this.setMail(mail);
        this.setMdp(mdp);
        this.setRole(role);

    }

    
    


    
}