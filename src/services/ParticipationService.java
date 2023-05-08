/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Event;
import entities.Participation;
import utils.connexionDB;

/**
 *
 * @author ZC-Lenovo
 */
public class ParticipationService implements ParticipationInterface {
    Connection cnx = connexionDB.getInstance().getConnexion();

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participation> fetchParticiation() {
        List<Participation> Events = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM participation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Participation p = new Participation();
                p.setId(rs.getInt(1));
                p.setIdEv(rs.getInt(2));
                p.setIdC(rs.getInt(3));
                
                p.setNom(rs.getString(4));
                p.setPrenom(rs.getString(5));
                p.setNomEv(rs.getString(6));
                 p.setDateEv(rs.getDate(7));
                
                Events.add(p);
            }
            
        } catch (SQLException ex) {
        }
        
        return Events;}
    public void deleteP(int id) {
    try {
        String req = "DELETE FROM `participation` WHERE `participation`.`id`='"+id+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Participation Deleted Successfully!");
    } catch (SQLException ex) {
        Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     public int Count(int id)
     { 
          
        try {
             String req = "SELECT COUNT(*) FROM participation WHERE id_evenement_id= ?";
           PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id); // set the parameter using setInt method
        System.out.println(req);
        ResultSet rs = st.executeQuery(); // don't pass the
        if (rs.next()) {
    int theCount = rs.getInt(1);
    System.out.println(theCount);
    return theCount;
        }           
}      catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     }
}
