/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author azizb
 */
public class Livreur {

    private int id;
    private String nom;
    private String prenom;
    private String secteur;
    private String telephone;
    private String password;
    private String type_livreur;

    public Livreur() {
    }

    public Livreur(String nom, String prenom, String secteur, String telephone, String password, String type_livreur) {
        this.nom = nom;
        this.prenom = prenom;
        this.secteur = secteur;
        this.telephone = telephone;
        this.password = password;
        this.type_livreur = type_livreur;
    }

    public Livreur(int id, String nom, String prenom, String secteur, String telephone, String password, String type_livreur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.secteur = secteur;
        this.telephone = telephone;
        this.password = password;
        this.type_livreur = type_livreur;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getType_livreur() {
        return type_livreur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType_livreur(String type_livreur) {
        this.type_livreur = type_livreur;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", nom=" + nom + ",prenom=" + prenom + ", secteur=" + secteur + ", telephone=" +telephone +", password=" +password +", type-lyvreur=" +type_livreur +  '}';
    }

}
