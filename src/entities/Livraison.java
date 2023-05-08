package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Livraison {

    private int id;
    private String nom_destination;
    private Date date_livraison;
    private String adresse_destination;
    private String telephone_destination;
    private Commande id_commande_id;
    private Livreur Livreur;

    public Livraison() {
    }

    public Livraison(String nom, Date date, String add, String tel, Commande commande, Livreur livreur) {
        this.nom_destination = nom;
        this.date_livraison = date;
        this.adresse_destination = add;
        this.telephone_destination = tel;
        this.id_commande_id = commande;
        this.Livreur = livreur;
    }

    public Livraison(int id, String nom, Date date, String add, String tel, Commande commande, Livreur livreur) {
        this.id = id;
        this.nom_destination = nom;
        this.date_livraison = date;
        this.adresse_destination = add;
        this.telephone_destination = tel;
        this.id_commande_id = commande;
        this.Livreur = livreur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_destination() {
        return nom_destination;
    }

    public void setNom_destination(String nom_destination) {
        this.nom_destination = nom_destination;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getAdresse_destination() {
        return adresse_destination;
    }

    public void setAdresse_destination(String adresse_destination) {
        this.adresse_destination = adresse_destination;
    }

    public String getTelephone_destination() {
        return telephone_destination;
    }

    public void setTelephone_destination(String telephone_destination) {
        this.telephone_destination = telephone_destination;
    }

    public Commande getId_commande_id() {
        return id_commande_id;
    }

    public void setId_commande_id(Commande commande) {
        this.id_commande_id = commande;
    }

    public Livreur getLivreur() {
        return Livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.Livreur = livreur;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", nom_destination=" + nom_destination + ", date_livraison=" + date_livraison + ", adresse_destination=" + adresse_destination + ", telephone_destination=" + telephone_destination + ", commande=" + id_commande_id + '}';
    }

}
