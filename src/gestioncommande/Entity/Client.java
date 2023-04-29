/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Entity;

/**
 *
 * @author Homrani
 */
public class Client {
    private int idCl;
    private String nomCl;
    private String prenomCl;
    private String adresseCl;

    public int getIdCl() {
        return idCl;
    }

    public void setIdCl(int idCl) {
        this.idCl = idCl;
    }

    public String getNomCl() {
        return nomCl;
    }

    public void setNomCl(String nomCl) {
        this.nomCl = nomCl;
    }

    public String getPrenomCl() {
        return prenomCl;
    }

    public void setPrenomCl(String prenomCl) {
        this.prenomCl = prenomCl;
    }

    public String getAdresseCl() {
        return adresseCl;
    }

    public void setAdresseCl(String adresseCl) {
        this.adresseCl = adresseCl;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" + "idCl=" + idCl + ", nomCl=" + nomCl + ", prenomCl=" + prenomCl + ", adresseCl=" + adresseCl + '}';
    }
    
    
}
