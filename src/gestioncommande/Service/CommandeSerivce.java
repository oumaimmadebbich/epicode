/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Service;

import gestioncommande.Entity.Commande;
import gestioncommande.IService.IService;
import gestioncommande.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
