/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.connexionDB;

/**
 *
 * @author trabe
 */
public class UserService {
        Statement ste;
   Connection conn = connexionDB.getInstance().getConnexion();
    
      public List<User> afficherUser(){
       List<User> list = new ArrayList<>();
       try {
           String req ="Select * from user";
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            User u = new User();
             u.setId(RS.getInt(1));
                u.setEmail(RS.getString("email"));
                u.setRoles(RS.getString("roles"));
                u.setPassword(RS.getString("password"));
                u.setNom(RS.getString("nom"));
                 u.setPrenom(RS.getString("prenom"));
                  u.setTelephone(RS.getString("telephone"));
             
                 
               list.add(u);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } return list;
    }
}
