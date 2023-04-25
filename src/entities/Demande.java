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
public class Demande {
    int id;
    Date datepostulation;
    String cv;
    String etat;
    int user_id;
    int offre_id;

    public Demande(int id, Date datepostulation, String cv, String etat, int user_id, int offre_id) {
        this.id = id;
        this.datepostulation = datepostulation;
        this.cv = cv;
        this.etat = etat;
        this.user_id = user_id;
        this.offre_id = offre_id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
     public Demande() {
       
    }

    public int getId() {
        return id;
    }

    public Date getDatepostulation() {
        return datepostulation;
    }

    public String getCv() {
        return cv;
    }

   

    public int getUser() {
        return user_id;
    }

    public int getOffre() {
        return offre_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatepostulation(Date datepostulation) {
        this.datepostulation = datepostulation;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

   

    public void setUser(int user_id) {
        this.user_id = user_id;
    }

    public void setOffre(int offre_id) {
        this.offre_id = offre_id;
    }
    

    public Demande(int id, Date datepostulation, String cv,  int user_id, int offre_id) {
        this.id = id;
        this.datepostulation = datepostulation;
        this.cv = cv;
        
        this.user_id = user_id;
        this.offre_id = offre_id;
    }

    public Demande(Date datepostulation, String cv, int user_id, int offre_id) {
        this.datepostulation = datepostulation;
        this.cv = cv;
       
        this.user_id = user_id;
        this.offre_id = offre_id;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ",etat="+etat+" datepostulation=" + datepostulation + ", cv=" + cv +  ", user_id=" + user_id + ", offre_id=" + offre_id + '}';
    }
    
}
