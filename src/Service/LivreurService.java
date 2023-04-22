/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Livreur;
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

/**
 *
 * @author azizb
 */
public class LivreurService implements Interface<Livreur> {

    private static LivreurService instance;


    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;

    private LivreurService() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static LivreurService getInstance() {
        if (instance == null) {
            instance = new LivreurService();
        }
        return instance;
    }

    @Override
    public void insert(Livreur d) {
        String req = "insert into `livreur`(`nom`, `prenom`, `secteur`, `telephone`,`password`,`type_livreur` ) VALUES ('" + d.getNom() + "','" + d.getPrenom() + "','" + d.getSecteur() + "','" + d.getTelephone() + "','" + d.getPassword() + "','" + d.getType_livreur() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Livreur o) {
        String req = "delete from livreur where id=" + o.getId();
        Livreur p = displayById(o.getId());

        if (p != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

//       public void supprimer(int id) {
// String query = "DELETE from personne where id ='"+id+"'";
//        try {
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(query);
//            System.out.println("INFO: Produit Deleted.");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    @Override
    public ObservableList<Livreur> displayAll() {
        String req = "select * from livreur";
        ObservableList<Livreur> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Livreur d = new Livreur();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setSecteur(rs.getString("Secteur"));
                d.setTelephone(rs.getString("Telephone"));
                d.setPassword(rs.getString("password"));
                d.setType_livreur(rs.getString("type_livreur"));
                list.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Livreur> displayAllList() {
        String req = "select * from livreur";
        List<Livreur> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Livreur d = new Livreur();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setSecteur(rs.getString("Secteur"));
                d.setTelephone(rs.getString("Telephone"));
                d.setPassword(rs.getString("password"));
                d.setType_livreur(rs.getString("type_livreur"));
                list.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Livreur displayById(int id) {
        String req = "select * from livreur where id =" + id;
        Livreur d = new Livreur();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            d.setId(rs.getInt("id"));
            d.setNom(rs.getString("nom"));
            d.setPrenom(rs.getString("prenom"));
            d.setSecteur(rs.getString("Secteur"));
            d.setTelephone(rs.getString("Telephone"));
            d.setPassword(rs.getString("password"));
            d.setType_livreur(rs.getString("type_livreur"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    @Override
    public boolean update(Livreur d) {
        String qry = "UPDATE livreur SET `nom`='" + d.getNom() + "',`prenom`='" + d.getPrenom() + "',`secteur`='" + d.getSecteur() + "',`telephone`='" + d.getTelephone() + "' WHERE `id`=" + d.getId() + ";";

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivreurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
