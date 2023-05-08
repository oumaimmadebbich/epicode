/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Demande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.connexionDB;

/**
 *
 * @author trabe
 */
public class DemandeService {
      Statement ste;
   Connection conn = connexionDB.getInstance().getConnexion();
     public void ajouterDemande(Demande d)  {
       
       try{
             String req ="INSERT INTO `demande`(`cv`,`datepostulation`, `user_id`, `offre_id` ) VALUES ('"+d.getCv()+"','"+d.getDatepostulation()+"','"+d.getUser()+"','"+d.getOffre()+"')";
           ste = conn.createStatement();
           ste.executeUpdate(req);
           System.err.println("Demande Ajoutèe!!!");
           
       } catch (SQLException ex){
           System.out.println("Demande non ajoutèe");
           System.out.println(ex.getMessage());
       }
   }
       public void supprimerDemande(int id){
       try {
           String req = "DELETE from `demande` WHERE id = " + id;
            ste = conn.createStatement();
           ste.executeUpdate(req);
           System.out.println("Demande Deleted!!!");
           
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
       public List<Demande> afficherDemande() throws SQLException{
       List<Demande> list = new ArrayList<>();
       try {
           String req ="Select * from demande";
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery(req);
           while (rs.next()) {
            Demande d = new Demande();
             d.setId  (rs.getInt("id"));
             d.setDatepostulation( rs.getDate("datepostulation"));
             d.setCv(rs.getString("cv"));
             d.setEtat(rs.getString("etat"));
                   
                   
                    
                   
                      d.setUser(rs.getInt("user_id"));
                        d.setOffre(rs.getInt("offre_id"));
             
                 
               list.add(d);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } return list;
    }
        public void modifierdemande (Demande d){
      
      try {
         
   String req ="UPDATE demande SET `cv`='"+d.getCv()+"',`date`='"+d.getDatepostulation()+"',`user_id`='"+d.getUser()+"',`offre_id`='"+d.getOffre()+"' WHERE id="+d.getId()+";";          

          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("demande updated !!");
          
      }catch (SQLException ex) {
          System.out.println("demande not Updated");
          System.out.println(ex.getMessage());
      }
    }   
         public int nbEncours(){
     String req="SELECT COUNT(*) FROM demande WHERE etat='En cours du traitement' ";
      
      int nb =0;
        
        try {
        	Statement stm = connexionDB.getInstance().getConnexion().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
     
     
     public int nbTraité(){
     String req="SELECT COUNT(*) FROM demande WHERE etat='traité' ";
      
      int nb =0;
        
        try {
        	Statement stm = connexionDB.getInstance().getConnexion().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
     public void traiterDemande(int id) {
    try {
        String req = "UPDATE demande SET  etat = 'Traité' WHERE id = " + id + ";";
        Statement st = conn.createStatement();
        st.executeUpdate(req);
        System.out.println("demande updated!!");
    } catch (SQLException ex) {
        System.out.println("demande not updated");
        System.out.println(ex.getMessage());
    }
}
     
    
}
