/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Entity;

import java.time.LocalDateTime;

/**
 *
 * @author Homrani
 */
public class Facture {
    private int idFacture;  
    private int idcmd;  
    private LocalDateTime datefacture;
    private int remise;
    private float montant;

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

    public LocalDateTime getDatefacture() {
        return datefacture;
    }

    public void setDatefacture(LocalDateTime datefacture) {
        this.datefacture = datefacture;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Facture(int idcmd, LocalDateTime datefacture, int remise, float montant) {
        this.idcmd = idcmd;
        this.datefacture = datefacture;
        this.remise = remise;
        this.montant = montant;
    }

    public Facture(int idcmd, LocalDateTime datefacture) {
        this.idcmd = idcmd;
        this.datefacture = datefacture;
    }

    public Facture() {
    }


@Override
public String toString() {
    return "Facture{" + "\n" +
           "idcmd=" + idcmd + "\n" +
           "datefacture=" + datefacture + "\n" +
           "remise=" + remise + "\n" +
           "montant=" + montant + "\n" +
           '}';
}
    
    
    
}
