package services;


import java.util.List;
import entities.Event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZC-Lenovo
 */
public interface EventInterface {
    
    public void addEvent(Event e);
    public void updateEvent(Event e);
    public void delete(int id);
    public Event getEV(int id);
    
    //list : select
    public List<Event> fetchEvents();
    
}
