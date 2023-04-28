/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entites.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author Imen
 */
public class CategorieService  implements IServices<Categorie>{
     private Connection conx;
  
    public CategorieService() {
         conx=MyConnection.getInstance().getCnx();
    }

    @Override
    public void insert(Categorie t) {
       try {
            String req = "INSERT INTO `categorie` (`nom_categorie`) VALUES (?)";
            PreparedStatement ps = conx.prepareStatement(req);
             
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println("ajout effectueé avec sucees");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE `id_categorie` ="+id;
           PreparedStatement pst = conx.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("categorie supprimée avec succees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Categorie t) {
        try {
            String req = "UPDATE categorie SET nom_categorie = '"+ t.getNom()+ "' WHERE id_categorie =" + t.getId();
            PreparedStatement ps = conx.prepareStatement(req);
            
            ps.executeUpdate(req);
            System.out.println("Categorie mise a jour avec success !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public List<Categorie> realAll() {
         List<Categorie> lstc = new ArrayList<>();
        try {
            Statement ste =conx.createStatement();
            String req = "SELECT * FROM `categorie`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
           
          Categorie resultCategorie = new Categorie(result.getInt("id_categorie"), result.getString("nom_categorie"));
            
            
           lstc.add(resultCategorie);
        }
        
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return lstc;
    }

    @Override
    public Categorie readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Categorie> insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 /* public Categorie SearchById(long id_categorie) throws SQLException{
        Statement stm = conx.createStatement();
        Categorie categorie = new Categorie();
        String query = "select * from categorie where id_categorie="+id;
        ResultSet result= stm.executeQuery(query);
        while (result.next()){
        Categorie.setId(result.getInt("id_categorie"));
        
        Categorie.setNom(result.getString("Nom_categorie"));
        
        return categorie;
    }*/
    
}
  
