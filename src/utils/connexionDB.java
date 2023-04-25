/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexionDB { 
    final String url ="jdbc:mysql://localhost:3306/user";
    final String login ="root";
    final String pwd="";
    private static connexionDB instance;
    Connection connexion;
    
    private connexionDB(){
        
        try {
            connexion =  DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion �tablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static connexionDB getInstance(){
    if (instance == null)
        instance = new connexionDB();
    return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
}