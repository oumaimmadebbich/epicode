/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author habac
 */
public class User {

    private int id;
    private String nom;
    private String email;
    private String telephone;
    private String role;
    private String password;

    public User() {
    }

    public User(String nom, String email, String telephone, String role, String password) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.password = password;
    }

    public User(int id, String nom, String email, String telephone, String role, String password) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return telephone;
    }

    public void setTel(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", telephone=" + telephone + ", role=" + role + ", password=" + password + '}';
    }

    public void setPrenom(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
