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
public class Commande {
    private int idcmd;
    private LocalDateTime datecmd;
    private int quantity;
    private float montant;
    private int idP;
    private int idCl;

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

    public LocalDateTime getDatecmd() {
        return datecmd;
    }

    public void setDatecmd(LocalDateTime datecmd) {
        this.datecmd = datecmd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdCl() {
        return idCl;
    }

    public void setIdCl(int idCl) {
        this.idCl = idCl;
    }

    public Commande() {
    }

    public Commande(int idcmd, LocalDateTime datecmd, int quantity, float montant, int idP, int idCl) {
        this.idcmd = idcmd;
        this.datecmd = datecmd;
        this.quantity = quantity;
        this.montant = montant;
        this.idP = idP;
        this.idCl = idCl;
    }

    public Commande(int idP, int idCl, int quantity, float montant,LocalDateTime datecmd) {
        this.datecmd = datecmd;
        this.quantity = quantity;
        this.montant = montant;
        this.idP = idP;
        this.idCl = idCl;
    }

    @Override
    public String toString() {
        return "Commande{" + "datecmd=" + datecmd + ", quantity=" + quantity + ", montant=" + montant + ", idP=" + idP + ", idCl=" + idCl + '}';
    }
    
    
}
