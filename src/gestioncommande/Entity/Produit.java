/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Entity;

/**
 *
 * @author Homrani
 */
public class Produit {
    private int idP;
    private String nom;
    private float prix;
    private int stock;

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Produit() {
    }

    @Override
    public String toString() {
        return "Produit{" + "idP=" + idP + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + '}';
    }
    
    
}
