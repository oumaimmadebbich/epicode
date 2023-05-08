/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Livraison;
import entities.Livreur;
import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.connexionDB;
/**
 *
 * @author azizb
 */
public class LivraisonService implements IService<Livraison>{
    
    private static LivraisonService instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
 Connection cnx = connexionDB.getInstance().getConnexion();
    
    private LivraisonService() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static LivraisonService getInstance(){
        if(instance==null) 
            instance=new LivraisonService();
        return instance;
    }

  
    public void insert(Livraison o) {
        String req="insert into `livraison` (`id_commande_id`, `id_livreur_id`, `nom_destination`, `adresse_destination`, `telephone_destination`, `date_livraison` ) VALUES ('" + o.getId_commande_id().getIdcmd() + "','" + o.getLivreur().getId() + "','" + o.getNom_destination() + "','" + o.getAdresse_destination() + "','" + o.getTelephone_destination() + "','" + o.getDate_livraison() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

 
    public void delete(Livraison o) {
        String req="delete from livraison where id="+o.getId();
        Livraison p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    public int insertProfile (Livraison o){
        return 0 ; 
    }
       

    
    
//       public void supprimer(int id) {
// String query = "DELETE from livraison where id ='"+id+"'";
//        try {
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(query);
//            System.out.println("INFO: Produit Deleted.");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }


   
  public ObservableList<Livraison> displayAll() {
    String req="select * from livraison";
    ObservableList<Livraison> list=FXCollections.observableArrayList();       
        
    try {
        rs=st.executeQuery(req);
        while(rs.next()){
            Livraison l=new Livraison();
            l.setId(rs.getInt("id"));
            l.setNom_destination(rs.getString("nom_destination"));
            l.setAdresse_destination(rs.getString("adresse_destination"));
            l.setTelephone_destination(rs.getString("Telephone_destination"));
            // Retrieve the value of Date_Livraison as a java.sql.Date object
            java.sql.Date date = rs.getDate("date_livraison");
            // Set the value of Date_Livraison using the java.sql.Date object
            l.setDate_livraison(date);
            list.add(l); // Ajoutez l'objet Livraison à la liste list
        }
            
    } catch (SQLException ex) {
        Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

    public List<Livraison> displayAllList() {
        String req="select * from livraison";
        List<Livraison> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Livraison f=new Livraison();
                f.setId(rs.getInt("id"));
                f.setNom_destination(rs.getString("nom_destination"));
                f.setAdresse_destination(rs.getString("adresse_destination"));
                f.setTelephone_destination(rs.getString("Telephone_destination"));
                
                            // Retrieve the value of Date_Livraison as a java.sql.Date object
            java.sql.Date date = rs.getDate("date_livraison");
            // Set the value of Date_Livraison using the java.sql.Date object
            f.setDate_livraison(date);
    
                list.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Livraison displayById(int id) {
           String req="select * from livraison where id ="+id;
           Livraison f=new Livraison();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
            f.setId(rs.getInt("id"));
            f.setNom_destination(rs.getString("nom_destination"));
            f.setAdresse_destination(rs.getString("adresse_destination"));
            f.setTelephone_destination(rs.getString("Telephone_destination"));
                        // Retrieve the value of Date_Livraison as a java.sql.Date object
            java.sql.Date date = rs.getDate("date_livraison");
            // Set the value of Date_Livraison using the java.sql.Date object
            f.setDate_livraison(date);

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return f;
    }
public boolean update(Livraison livraison) throws SQLException {
   String qry = "UPDATE livraison SET nom_destination=?, adresse_destination=?, telephone_destination=? WHERE id=?";

 PreparedStatement ps = cnx.prepareStatement(qry);
 ps.setString(1, livraison.getNom_destination());
ps.setString(2, livraison.getAdresse_destination());
ps.setString(3, livraison.getTelephone_destination());
ps.setInt(4, livraison.getId());
ps.executeUpdate();
    try {
        if (st.executeUpdate(qry) > 0) {
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

     public List<Livraison> displayAllList1(int id) {
        String req="select * from livraison where id_livreur_id =" +id;
        List<Livraison> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Livraison f=new Livraison();
                f.setId(rs.getInt("id"));
                f.setNom_destination(rs.getString("nom_destination"));
                f.setAdresse_destination(rs.getString("adresse_destination"));
                f.setTelephone_destination(rs.getString("Telephone_destination"));
                            // Retrieve the value of Date_Livraison as a java.sql.Date object
            java.sql.Date date = rs.getDate("date_livraison");
            // Set the value of Date_Livraison using the java.sql.Date object
            f.setDate_livraison(date);
    
                list.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void ajouter(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livraison> afficher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(int id, Livraison t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
