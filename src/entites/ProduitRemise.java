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
public class ProduitRemise {
    private String nomProduit;
    private float prix;
    private int remise;
    private float prixRemise;

    public ProduitRemise() {
    }

    public ProduitRemise(String nomProduit, float prix, int remise, float prixRemise) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.remise = remise;
        this.prixRemise = prixRemise;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    public float getPrixRemise() {
        return prixRemise;
    }

    public void setPrixRemise(float prixRemise) {
        this.prixRemise = prixRemise;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nomProduit);
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
        final ProduitRemise other = (ProduitRemise) obj;
        if (!Objects.equals(this.nomProduit, other.nomProduit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProduitRemise{" + "nomProduit=" + nomProduit + ", prix=" + prix + ", remise=" + remise + ", prixRemise=" + prixRemise + '}';
    }
    
}
