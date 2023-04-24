/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Event;
import models.Participation;
import util.MyConnection;

/**
 *
 * @author ZC-Lenovo
 */
public class ParticipationService implements ParticipationInterface {
    Connection cnx = MyConnection.getInstance().getCnx();

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
    
}
