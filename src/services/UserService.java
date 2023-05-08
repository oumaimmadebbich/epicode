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
import entities.User;
import utils.connexionDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.rmi.transport.Transport;
import utils.Session;
import java.util.Properties;


/**
 *
 * @author habac
 */
public class UserService {

    private PreparedStatement pste;

    Connection cnx = connexionDB.getInstance().getConnexion();

    public void ajouter2(User u) {

        try {
            String req = "INSERT INTO `user` (`email`,`role`,`password`, `telephone`, `nom`, `prenom`) VALUE ('" + u.getEmail() + "','" + u.getRole() + "','" + u.getPassword()
                    + "','" + u.getTel() + "','" + u.getNom() + "')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("utilisateur créée");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
            return password; // En cas d'erreur, retourne le mot de passe non crypté
        }
    }

    public void ajouter(User u) {
        String req = "INSERT INTO `user` (`role`,`nom`,`email`, `password`, `telephone`) VALUE (?,?,?,?,?)";
        try {
            pste = cnx.prepareStatement(req);
            pste.setString(1, u.getRole());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getEmail());
            pste.setString(4, encryptPassword(u.getPassword()));
            //pste.setString(4, u.getPassword());
            pste.setString(5, u.getTel());
            pste.executeUpdate();
            System.out.println("utilisateur créée");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean emailExists(String email) {
        try {
            String query = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'email : " + e.getMessage());
        }
        return false;
    }

    public void modifier(User u) {
        try {
            String req = "Update `user` SET `password`='" + encryptPassword(u.getPassword()) + "',`role`='" + u.getRole() + "',`telephone`='" + u.getTel() + "', `nom`='" + u.getNom() + "' WHERE nom='" + u.getNom() + "'";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le user  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier2(int id, User u) {
        String req = "Update `user` set role =? , nom=? , email=? , passwrd=? , telephone=? where id=" + id;
        try {
            pste = cnx.prepareStatement(req);
            pste.setString(1, u.getRole());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getEmail());
            pste.setString(4, u.getPassword());
            pste.setString(5, u.getTel());
            pste.executeUpdate();
            System.out.println("utilisateur modifié");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimer(User u) {
        String req = "delete from `user` where id=?";
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, u.getId());
            pste.executeUpdate();
            System.out.println("utilisateur supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimer2(int id) {
        String req = "delete from `user` where id=" + id;
        try {
            pste = cnx.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("utilisateur supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> afficher() {

        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setEmail(rs.getString(2));
                u.setRole(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setTel(rs.getString(5));
                u.setNom(rs.getString(6));
                

                users.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(users);
        return users;
    }

    public String verifierData(String id, String password) { //Login user 

        PreparedStatement stmt = null;
        ResultSet rst = null;
        String resultat = "";
        try {
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, password);
            rst = stmt.executeQuery();
            if (rst.next()) {
                resultat = rst.getString("role");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }

    public void set_token(String email, String pwd) {
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "UPDATE user SET token = 1 where email = '" + email + "' and password ='" + pwd + "';";
            stmt = cnx.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reset_token() {

        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE user SET token = 0 ";
            stmt = cnx.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int current_user() {

        PreparedStatement stmt = null;
        ResultSet rst = null;
        int id = 0;
        try {
            String sql = "SELECT id FROM user where token = 1 ;";
            stmt = cnx.prepareStatement(sql);
            rst = stmt.executeQuery();
            rst.next();
            id = rst.getInt("id");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public User afficher_user(int id) {

        User u = new User();
        String req = "SELECT * FROM `user` where id = " + id;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setTel(rs.getString(5));
                u.setRole(rs.getString(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean Authentification(User u) {
        boolean status = false;
        try {
            String req = "select * from user where email=? ";
            PreparedStatement st;
            st = cnx.prepareStatement(req);
            st.setString(1, u.getEmail());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (u.getPassword().equals(rs.getString("password"))) {

                    status = true;
                    u = this.findById(rs.getInt("id"));

                    Session.setUser(u);

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {
        }
        return status;
    }

    public User findById(int idconnected) {
        User us = null;
        try {
            String req = "select  id,`role`,`nom`,`email`, `password`, `telephone` from user where id=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idconnected);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //     us = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8));

            }
        } catch (SQLException ex) {
        }
        return us;
    }

    public String checkRole(String email) {
        String default_return = "ND";
        try {
            String req;
            req = "select role from user where email=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

             if (rs.next()) {
            String role = rs.getString(1);
            if (role.equals("Admin")) {
                return "Admin";
            } else if (role.equals("Client")) {
                return "Client";
            } else if (role.equals("Livreur")) {
                return "Livreur";
            }
        }

        } catch (SQLException ex) {
        }
        return default_return;
    }

    public User FindByEmail(String email) throws SQLException {
        User U = new User();
        String requete1 = "select * FROM user where email='" + email + "'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete1);
            while (rs.next()) {

                U.setId(rs.getInt("id"));
                U.setNom(rs.getString("nom"));
                U.setEmail(rs.getString("email"));
                U.setPassword(rs.getString("password"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U; //To change body of generated methods, choose Tools | Templates.
    }

}
