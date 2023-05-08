/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import entities.User;
import entities.Commande;
import entites.Produit;
import interfaces.IService;
import utils.connexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Homrani
 */
public class CommandeSerivce implements IService<Commande> {

    Connection cnx;

    public CommandeSerivce() {
        cnx = connexionDB.getInstance().getConnexion();
    }

    {

    }

    @Override
    public void ajouter(Commande commande) {
        Statement st;
        try {
            st = cnx.createStatement();
            String query = "INSERT INTO `commande`(`datecommande`, `quantity`, `montant`, `idP`, `idCl`) VALUES ('" + commande.getDatecmd() + "','" + commande.getQuantity() + "','" + commande.getMontant() + "','" + commande.getIdP() + "','" + commande.getIdCl() + "')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> afficher() throws SQLException {
        List<Commande> LC = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM commande";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            Commande commande = new Commande();
            commande.setIdcmd(rs.getInt("idcmd"));
            commande.setDatecmd(rs.getTimestamp(2).toLocalDateTime());
            commande.setQuantity(rs.getInt("quantity"));
            commande.setMontant(rs.getFloat("montant"));
            commande.setIdP(rs.getInt("idP"));
            commande.setIdCl(rs.getInt("idCl"));
            LC.add(commande);
        }

        return LC;
    }

    @Override
    public void supprimer(int idcmd) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete  from commande where idcmd =" + idcmd;
        stm.executeUpdate(query);
    }

    public Commande SearchById(long idcmd) throws SQLException {
        Statement stm = cnx.createStatement();
        Commande commande = new Commande();
        String query = "select * from commande where idcmd=" + idcmd;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            commande.setIdcmd(rs.getInt("idcmd"));
            commande.setDatecmd(rs.getTimestamp(2).toLocalDateTime());
            commande.setQuantity(rs.getInt("quantity"));
            commande.setMontant(rs.getFloat("montant"));
            commande.setIdP(rs.getInt("idP"));
            commande.setIdCl(rs.getInt("idCl"));;
        }
        return commande;
    }

    @Override
    public ObservableList<Commande> displayAll() {
        String query = "SELECT * FROM commande";
        ObservableList<Commande> list = FXCollections.observableArrayList();

        try {
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Commande l = new Commande();
                l.setIdcmd(rs.getInt("idcmd"));
                l.setDatecmd(rs.getTimestamp(2).toLocalDateTime());
                l.setQuantity(rs.getInt("quantity"));
                l.setMontant(rs.getFloat("montant"));
                l.setIdP(rs.getInt("idP"));
                l.setIdCl(rs.getInt("idCl"));
                list.add(l); 
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void modifier(int idcmdModifier, Commande commande) throws SQLException {
        Statement stm = cnx.createStatement();
        Commande r = SearchById(idcmdModifier);
        String query = "UPDATE `commande` SET `datecommande`='" + LocalDateTime.now() + "',`quantity`='" + commande.getQuantity() + "',`montant`='" + commande.getMontant() + "',`idP`='" + commande.getIdP() + "',`idCl`='" + commande.getIdCl() + "' where idcmd =" + r.getIdcmd();
        stm.executeUpdate(query);
    }

    public List<String> getClientByName() {
        List<String> Lc = new ArrayList<>();
        try {

            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Lc.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lc;
    }

    public User findNomClient(String name) {
        User user = new User();
        try {
            Statement stm = cnx.createStatement();

            String query = "select * from user where nom='" + name + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<String> getProduitByName() {
        List<String> Lc = new ArrayList<>();
        try {

            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM produit";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Lc.add(rs.getString("titre"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lc;
    }

    public Produit findNomProduit(String name) {
        Produit produit = new Produit();
        try {
            Statement stm = cnx.createStatement();

            String query = "select * from produit where titre='" + name + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                produit.setId(rs.getInt("id_produit"));
                produit.setTitre(rs.getString("titre"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setQuantite(rs.getInt("quantite"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produit;
    }

    public float maxPrice() {
        float maxPrice = 0;
        try {
            ResultSet set = connexionDB.getInstance()
                    .getConnexion()
                    .prepareStatement("SELECT MAX(montant) FROM commande")
                    .executeQuery();
            if (set.next()) {
                maxPrice = set.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxPrice;
    }
}
