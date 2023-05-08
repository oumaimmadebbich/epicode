/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
import entities.Event;
import utils.connexionDB;

/**
 *
 * @author ZC-Lenovo
 */
public class EventService  implements EventInterface {
Connection cnx = connexionDB.getInstance().getConnexion();
    @Override
    public void addEvent(Event e) {
       try {
            
            String req = "INSERT INTO `event`(`title_event`, `description_event`, `time_event`,`organisation`,`image_event`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getTitleEvent());
            ps.setString(2, e.getDescriptionEvent());
           
            ps.setString(4, e.getOrganisation());
            
            Date date=e.getTimeEvent();
            ps.setDate(3, date);
            ps.setString(5, e.getImageEvent());
            ps.executeUpdate();
            System.out.println("Event Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Event> fetchEvents() {
       List<Event> Events = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Event p = new Event();
                p.setIdEvent(rs.getInt(1));
                p.setTitleEvent(rs.getString(2));
                p.setDescriptionEvent(rs.getString(4));
                
                p.setImageEvent(rs.getString(3));
                p.setTimeEvent(rs.getDate(5));
                p.setOrganisation(rs.getString(6));
                
                Events.add(p);
            }
            
        } catch (SQLException ex) {
        }
        
        return Events;
    }

    @Override
    public void updateEvent(Event e) {
    try {
        String req = "UPDATE `event` SET `title_event`='" + e.getTitleEvent() + "', `time_event`='" + e.getTimeEvent() + "',`image_event`='"+ e.getImageEvent() +"', `description_event`='" + e.getDescriptionEvent() + "', `organisation`='" + e.getOrganisation() + "' WHERE `event`.`id`='" + e.getIdEvent() + "';";

        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Event Updated Successfully!");
    } catch (SQLException ex) {
        Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    @Override
    public void delete(int id) {
    try {
       
        PreparedStatement pst = cnx.prepareStatement("DELETE FROM participation WHERE id_evenement_id = ?");
        pst.setInt(1, id);
        pst.executeUpdate();
        pst = cnx.prepareStatement("DELETE FROM event WHERE id = ?");
        pst.setInt(1, id);
        pst.executeUpdate();
       
    } catch (Exception e) {
        System.err.println("Got an exception! ");
        System.err.println(e.getMessage());
    }
    
    }
  public Event getEV(int id) {
    try {
        String req = "SELECT * FROM event WHERE id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id); // set the parameter using setInt method
        System.out.println(req);
        ResultSet rs = st.executeQuery(); // don't pass the query to executeQuery method
        if (rs.next()) {
            Event p = new Event();
            p.setIdEvent(rs.getInt("id"));
            p.setTitleEvent(rs.getString("title_event"));
            p.setDescriptionEvent(rs.getString("description_event"));
            p.setImageEvent(rs.getString("image_event"));
            p.setTimeEvent(rs.getDate("time_event"));
            p.setOrganisation(rs.getString("organisation"));
            return p;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}








    
}
