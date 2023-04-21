/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author oumaima debbich
 */
public class Produit {
    private int id;
    private String titre;
    private float prix;
    private String description;
    private String image;
    private int quantite;
    private Categorie categorie;
    private int categ;

    public int getCateg() {
        return categ;
    }

    public void setCateg(int categ) {
        this.categ = categ;
    }

    public Produit(int id, String titre, float prix, String description, String image, int quantite, int categ) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.categ = categ;
    }

    public Produit(String titre, float prix, String description, String image, int quantite, Categorie categorie, int categ) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
        this.categ = categ;
    }

    public Produit(int id, String titre, float prix, String description, String image, int quantite, Categorie categorie, int categ) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
        this.categ = categ;
    }

    public Produit() {
    }

    public Produit(int id, String titre, float prix, String description, String image, int quantite, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
    }

    public Produit(String titre, float prix, String description, String image, int quantite, int categ) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.categ = categ;
    }

    public Produit(String titre, float prix, String description, String image, int quantite, Categorie categorie) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
    }

    public Produit(String titre, float prix, String description, String image, int quantite) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.titre);
        hash = 89 * hash + Objects.hashCode(this.categorie);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", titre=" + titre + ", prix=" + prix + ", description=" + description + ", image=" + image + ", quantite=" + quantite + ", id_categorie=" + categorie.getId() + '}'+"\n";
    }
    
}

