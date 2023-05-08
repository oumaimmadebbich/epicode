/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author ZC-Lenovo
 */
public class Participation {
    int id;
    int idEv;

    public Participation() {
    }
    int idC;
    String nom;
    String prenom;
    String nomEv;
    Date DateEv;

    public Participation(int id, int idEv, int idC, String nom, String prenom, String nomEv, Date DateEv) {
        this.id = id;
        this.idEv = idEv;
        this.idC = idC;
        this.nom = nom;
        this.prenom = prenom;
        this.nomEv = nomEv;
        this.DateEv = DateEv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
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

    public String getNomEv() {
        return nomEv;
    }

    public void setNomEv(String nomEv) {
        this.nomEv = nomEv;
    }

    public Date getDateEv() {
        return DateEv;
    }

    public void setDateEv(Date DateEv) {
        this.DateEv = DateEv;
    }
    
}
