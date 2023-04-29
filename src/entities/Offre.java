/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author trabe
 */
public class Offre {
     private int id;
    private String titre ;
    private Date datelimite;
    private String description;
    private String experience ;

    public Offre() {
       
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", titre=" + titre + ", datelimite=" + datelimite + ", description=" + description + ", experience=" + experience + '}';
    }

    public Offre(String titre, Date datelimite, String description, String experience) {
        this.titre = titre;
        this.datelimite = datelimite;
        this.description = description;
        this.experience = experience;
    }

    public Offre(int id, String titre, Date datelimite, String description, String experience) {
        this.id = id;
        this.titre = titre;
        this.datelimite = datelimite;
        this.description = description;
        this.experience = experience;
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

    public Date getDatelimite() {
        return datelimite;
    }

    public void setDatelimite(Date datelimite) {
        this.datelimite = datelimite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
