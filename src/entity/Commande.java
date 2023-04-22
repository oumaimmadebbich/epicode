package entity;

import java.sql.Date;

public class Commande {
    
    private int id;
    private int id_panier;
    private Date datecommande;
    private String etatcommande;

    public Commande() {
    }

    public Commande(int id_panier, Date datecommande, String etatcommande) {
        this.id_panier = id_panier;
        this.datecommande = datecommande;
        this.etatcommande = etatcommande;
    }

    public Commande(int id, int id_panier, Date datecommande, String etatcommande) {
        this.id = id;
        this.id_panier = id_panier;
        this.datecommande = datecommande;
        this.etatcommande = etatcommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public String getEtatcommande() {
        return etatcommande;
    }

    public void setEtatcommande(String etatcommande) {
        this.etatcommande = etatcommande;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", id_panier=" + id_panier + ", datecommande=" + datecommande + ", etatcommande=" + etatcommande + '}';
    }   
}
