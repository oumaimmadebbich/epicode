/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.time.LocalDateTime;
import models.Event;
import services.EventInterface;
import services.EventService;


/**
 *
 * @author ZC-Lenovo
 */
public class Javaproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           EventInterface ps = new EventService();
           
        //player init
        Event e = new Event();
        
        e.setTitleEvent("Germany");
        e.setDescriptionEvent("Muller");
       
        e.setOrganisation("Munich");
//        
//        //add action
      // ps.addEvent(e);
        ps.delete(3);
        //select
        //System.out.println(ps.fetchEvents());
        //ps.updateEvent(e);
    }
    
}
