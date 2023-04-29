/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Service;

import gestioncommande.Entity.Client;
import gestioncommande.Entity.Commande;
import gestioncommande.Entity.Produit;
import gestioncommande.IService.IService;
import gestioncommande.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Homrani
 */
public class CommandeSerivce implements IService<Commande>{
        Connection cnx;
    public CommandeSerivce (){
        cnx = Myconnexion.getInstance().getCnx();}{
    
}

    @Override
    public void ajouter(Commande commande) {
               Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `commande`(`datecmd`, `quantity`, `montant`, `idP`, `idCl`) VALUES ('"+commande.getDatecmd()+"','"+commande.getQuantity()+"','"+commande.getMontant()+"','"+commande.getIdP()+"','"+commande.getIdCl()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
    }

    @Override
    public List<Commande> afficher() throws SQLException {
        List<Commande> LC = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM commande";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
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
        String query = "delete from commande where idcmd =" + idcmd;
        stm.executeUpdate(query);
    }
    
    public Commande SearchById(long idcmd) throws SQLException{
        Statement stm = cnx.createStatement();
        Commande commande = new Commande();
        String query = "select * from commande where idcmd="+idcmd;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        commande.setIdcmd(rs.getInt("idcmd"));
        commande.setDatecmd(rs.getTimestamp(2).toLocalDateTime());
        commande.setQuantity(rs.getInt("quantity"));
        commande.setMontant(rs.getFloat("montant"));
        commande.setIdP(rs.getInt("idP"));
        commande.setIdCl(rs.getInt("idCl"));;}
        return commande;
    }
    @Override
    public void modifier(int idcmdModifier, Commande commande) throws SQLException {
        Statement stm = cnx.createStatement();
        Commande r =SearchById(idcmdModifier);
        String query = "UPDATE `commande` SET `datecmd`='"+LocalDateTime.now()+"',`quantity`='"+commande.getQuantity()+"',`montant`='"+commande.getMontant()+"',`idP`='"+commande.getIdP()+"',`idCl`='"+commande.getIdCl()+"' where idcmd ="+r.getIdcmd();
        stm.executeUpdate(query);
    }
    
        public List<String> getClientByName()  {
                 List<String> Lc = new ArrayList<>();
            try {

                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM client";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                    Lc.add(rs.getString("nomCl"));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
            }
             return Lc;
    }
 
    public Client findNomClient (String name) {
                  Client client =new Client();
            try {
                Statement stm = cnx.createStatement();

                String query = "select * from client where nomCl='"+name + "'";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                    client.setIdCl(rs.getInt("idCl"));
                    client.setNomCl(rs.getString("nomCl"));
                    client.setPrenomCl(rs.getString("prenomCl"));
                    client.setAdresseCl(rs.getString("adresseCl"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
            }
             return client;
    }        
        public List<String> getProduitByName()  {
                 List<String> Lc = new ArrayList<>();
            try {

                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM produit";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                    Lc.add(rs.getString("nom"));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
            }
             return Lc;
    }      
    public Produit findNomProduit (String name) {
                  Produit produit =new Produit();
            try {
                Statement stm = cnx.createStatement();

                String query = "select * from produit where nom='"+name + "'";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                    produit.setIdP(rs.getInt("idP"));
                    produit.setNom(rs.getString("nom"));
                    produit.setPrix(rs.getFloat("prix"));
                    produit.setStock(rs.getInt("stock"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(CommandeSerivce.class.getName()).log(Level.SEVERE, null, ex);
            }
             return produit;
    } 
    public float maxPrice() {
        float maxPrice = 0;
        try {
        ResultSet set = Myconnexion.getInstance()
            .getCnx()
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
