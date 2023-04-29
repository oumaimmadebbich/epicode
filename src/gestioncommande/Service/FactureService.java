/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Service;


import gestioncommande.Entity.Commande;
import gestioncommande.Entity.Facture;
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
public class FactureService implements IService<Facture>{
        Connection cnx;
    public FactureService (){
        cnx = Myconnexion.getInstance().getCnx();}{
    
}


    @Override
    public List<Facture> afficher() throws SQLException {
        List<Facture> lf = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM commande";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Facture facture = new Facture();   
        facture.setIdFacture(rs.getInt("idFacture"));
        facture.setIdcmd(rs.getInt("idcmd"));
        facture.setDatefacture(rs.getTimestamp(2).toLocalDateTime());
        facture.setRemise(rs.getInt("remise"));
        facture.setMontant(rs.getFloat("montant"));
        lf.add(facture);
        }
        
        return lf;
    }

    @Override
    public void supprimer(int idFacture) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from facture where idFacture =" + idFacture;
        stm.executeUpdate(query);
    }
    public Facture SearchById(long idFacture) throws SQLException{
        Statement stm = cnx.createStatement();
        Facture facture = new Facture();
        String query = "select * from facture where idFacture="+idFacture;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        facture.setIdFacture(rs.getInt("idFacture"));
        facture.setIdcmd(rs.getInt("idcmd"));
        facture.setDatefacture(rs.getTimestamp(2).toLocalDateTime());
        facture.setRemise(rs.getInt("remise"));
        facture.setMontant(rs.getFloat("montant"));}
        return facture;
    }
    
    
    public void modifier(int idfactureModifier,int idcmd, Facture facture) throws SQLException {
        Statement stm = cnx.createStatement();
        Facture f =SearchById(idfactureModifier);
        Commande c =SearchCommandeById(idcmd);
        String query = "UPDATE `facture`(`idcmd `, `datefacture`, `remise`, `montant`) VALUES ('"+facture.getIdcmd()+"','"+facture.getDatefacture()+"','"+facture.getRemise()+"','"+c.getMontant()*facture.getRemise()+"' where idcmd ="+f.getIdFacture();
        stm.executeUpdate(query);
    }
    
        public Commande SearchCommandeById(long idcmd) throws SQLException{
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
    
    public void AjouterFacture(int idcmd, Facture facture) throws SQLException {
        Commande c =SearchCommandeById(idcmd);
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `facture`(`idcmd`, `datefacture`, `remise`, `montant`) VALUES ('"+facture.getIdcmd()+"','"+facture.getDatefacture()+"','"+facture.getRemise()+"','"+c.getMontant()*((100-facture.getRemise())*0.01)+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
    }

    @Override
    public void ajouter(Facture t) {
    }

    @Override
    public void modifier(int id, Facture t) throws SQLException {
    }
}
