/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.connexionDB;

/**
 *
 * @author trabe
 */
public class OffreService {
    Statement ste;
   Connection conn = connexionDB.getInstance().getConnexion();
   
    
   public void ajouterOffre(Offre f)  {
       
       try{
           String req ="INSERT INTO `offre` (`titre`,`datelimite`,`description`,`experience`) VALUES ('" + f.getTitre() + "','" + f.getDatelimite()+ "' ,'" + f.getDescription() + "','" + f.getExperience()+"')";
           ste = conn.createStatement();
           ste.executeUpdate(req);
           System.err.println("Offre Ajouter!!!");
           
       } catch (SQLException ex){
           System.out.println("Offre non ajoute");
           System.out.println(ex.getMessage());
       }
   }
   
    public void supprimerOffre(int id){
       try {
           String req = "DELETE from `offre` WHERE Idoffre = " + id;
            ste = conn.createStatement();
           ste.executeUpdate(req);
           System.out.println("Offre Deleted!!!");
           
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
    
    public List<Offre> afficherOffre(){
       List<Offre> list = new ArrayList<>();
       try {
           String req ="Select * from offre";
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Offre f = new Offre();
             f.setId(RS.getInt(1));
                f.setTitre(RS.getString("titre"));
                f.setDatelimite(RS.getDate("datelimite"));
                f.setDescription(RS.getString("description"));
                f.setExperience(RS.getString("experience"));
             
                 
               list.add(f);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } return list;
    }
       
    public void modifieroffre (Offre F){
      
      try {
         
          String req ="UPDATE offre SET `titre`='"+F.getTitre()+"',`datelimite`='"+F.getDatelimite()+"',`description`='"+F.getDescription()+"',`experience`='"+F.getExperience()+"' WHERE Idoffre="+F.getId()+";";

          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("offre updated !!");
          
      }catch (SQLException ex) {
          System.out.println("offre not Updated");
          System.out.println(ex.getMessage());
      }
    }
    public  List<Object> getTitresEtDemandes() {
    List<Object> resultat = new ArrayList<>();
try {
    String req = "SELECT offre.titre, COUNT(demande.id) AS nombre_demandes\n" +
                 "FROM offre\n" +
                 "LEFT JOIN demande ON offre.Idoffre = demande.offre_id\n" +
                 "GROUP BY offre.Idoffre";
    Statement st = conn.createStatement();
    ResultSet RS = st.executeQuery(req);

    // Stockage des valeurs dans des listes
    List<String> titres = new ArrayList<>();
    List<Integer> demandes = new ArrayList<>();
    while (RS.next()) {
        String titreOffre = RS.getString("titre");
        int nombreDemandes = RS.getInt("nombre_demandes");
        titres.add(titreOffre);
        demandes.add(nombreDemandes);
    }

    // Ajout des listes à la variable resultat
    resultat.add(titres);
    resultat.add(demandes);
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
return resultat;
    }
    public ObservableList<Offre> chercherOffre(String chaine) {
        String sql = "SELECT * FROM offre WHERE (titre LIKE ? or experience LIKE ? or datelimite Like ?  ) order by titre ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        ObservableList<Offre> myList = FXCollections.observableArrayList();
        try {

            Statement ste = conn.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = conn.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                Offre f = new Offre();

                f.setTitre(rs.getString("titre"));
                f.setExperience(rs.getString("experience"));
                f.setDescription(rs.getString("description"));
                
                f.setDatelimite(rs.getDate("datelimite"));
                
                f.setId(rs.getInt("Idoffre"));

                myList.add(f);
                System.out.println("offre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
 public Offre afficher1Offre(int id) {
    Offre f = new Offre();
    try {
        String req = "SELECT * FROM offre WHERE Idoffre = " + id;
        Statement st = conn.createStatement();
        ResultSet RS = st.executeQuery(req);

        if (RS.next()) { // Appel de la méthode next() pour déplacer le curseur sur la première ligne des résultats
            f.setId(RS.getInt("Idoffre"));
            f.setTitre(RS.getString("titre"));
            f.setDatelimite(RS.getDate("datelimite"));
            f.setDescription(RS.getString("description"));
            f.setExperience(RS.getString("experience"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return f;
}


}
