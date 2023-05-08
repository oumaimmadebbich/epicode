/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import services.UserService;



public class Session {
     private static final UserService us = new UserService();
    
    private static Session instance = null;
    private  static User user = null;

    
 public final static Session getInstance(User userConnected) {

        if (Session.instance == null) {
            Session.instance = new Session(userConnected);

        }
        System.out.println(Session.instance);
        return Session.instance;
    }

  
    
    private Session(User userConnected) {
        super();
        Session.user = userConnected;
    }
    
   
    public final static Session getFirstInstance(User userConnected) {

        if (Session.instance == null) {

            Session.instance = new Session(userConnected);
         //   System.out.println(userConnected.getId());
      
        }
        return Session.instance;
    }

    public static UserService getFs() {
        return us;
    }

    public static Session getInstance() {
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public Session() {
    }


    public static void setUser(User user) {
        Session.user = user;
    }


   
 
    

}