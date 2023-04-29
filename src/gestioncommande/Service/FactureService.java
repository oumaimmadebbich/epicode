/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncommande.Service;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestioncommande.Entity.Commande;
import gestioncommande.Entity.Facture;
import gestioncommande.IService.IService;
import gestioncommande.Utils.Myconnexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author sabri
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
        String query = "SELECT * FROM facture";
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
        int f = 1;
        Commande c =SearchCommandeById(idcmd);
        if (c.getMontant() < 50) {
            // First condition: less than 50
            System.out.println("No remise");
            facture.setRemise(0);
            f = facture.getRemise();
        }
        else if (c.getMontant() >= 50 && c.getMontant() <= 100) {
            // Second condition: between 50 and 100 (inclusive)
            System.out.println("Remise 15%");
            facture.setRemise(15);
            f = facture.getRemise();
        }
        else if (c.getMontant() > 100) {
            // Third condition: greater than 100
            System.out.println("Remise 25%");
            facture.setRemise(25);
            f = facture.getRemise();
        }

        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `facture`(`idcmd`, `datefacture`, `remise`, `montant`) VALUES ('"+facture.getIdcmd()+"','"+facture.getDatefacture()+"','"+facture.getRemise()+"','"+c.getMontant()*((100-f)*0.01)+"')";
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
    
    public void exporterPDF()  {
      try{
        // this lines to get desktop path
        FileSystemView view = FileSystemView.getFileSystemView();
        File file = view.getHomeDirectory();
        String path = file.getPath();

        // this line to prepare the generated file path
        String file_name = file.getPath() + "/facture.pdf";

        // creating the file 
        Document document = new Document();
        // generating the file
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        // start editing file
        document.open();
        // create title
        Paragraph para = new Paragraph("Liste des factures");
        // centre the title
        para.setAlignment(Element.ALIGN_CENTER);
        // add title to the file
        document.add(para);
        // create space between table and title
        Paragraph para2 = new Paragraph("\n");
        document.add(para2);

        // get the list of elements to display
        List<Facture> facturesList = afficher(); // replace with method to get list of Facture objects

        // create table of 5 columns
        PdfPTable table = new PdfPTable(5);
        // column 1
        PdfPCell cl = new PdfPCell(new Phrase("ID Facture"));
        table.addCell(cl);
        // column 2
        PdfPCell cl1 = new PdfPCell(new Phrase("ID Commande"));
        table.addCell(cl1);
        // column 3
        PdfPCell cl2 = new PdfPCell(new Phrase("Date de facture"));
        table.addCell(cl2);
        // column 4
        PdfPCell cl3 = new PdfPCell(new Phrase("Remise"));
        table.addCell(cl3);
        // column 5
        PdfPCell cl4 = new PdfPCell(new Phrase("Montant"));
        table.addCell(cl4);

        // set titles of the columns
        table.setHeaderRows(1);

        // filling the table 
        for (int i = 0; i < facturesList.size(); i++) {
            Facture facture = facturesList.get(i);
            table.addCell(Integer.toString(facture.getIdFacture()));
            table.addCell(Integer.toString(facture.getIdcmd()));
            table.addCell(facture.getDatefacture().toString());
            table.addCell(Integer.toString(facture.getRemise()));
            table.addCell(Float.toString(facture.getMontant()));
        }
        // add the table to the file
        document.add(table);

        // save the file
        document.close();
        // open the file
        Desktop.getDesktop().open(new File(file_name));
      }
      catch(Exception e){
          e.printStackTrace();
      }
    }
    
    public float maxPrice() {
        float maxPrice = 0;
        try {
        ResultSet set = Myconnexion.getInstance()
            .getCnx()
            .prepareStatement("SELECT MAX(montant) FROM facture")
            .executeQuery();
        if (set.next()) {
            maxPrice = set.getFloat(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(FactureService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return maxPrice;
}
    public Facture getFactureWithMaxPrice() throws SQLException {

        Statement stm = cnx.createStatement();
        Facture facture = new Facture();
        String query ="SELECT * FROM facture WHERE montant = (SELECT MAX(montant) FROM facture)";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        facture.setIdFacture(rs.getInt("idFacture"));
        facture.setIdcmd(rs.getInt("idcmd"));
        facture.setDatefacture(rs.getTimestamp(2).toLocalDateTime());
        facture.setRemise(rs.getInt("remise"));
        facture.setMontant(rs.getFloat("montant"));}
            // Set any other fields as needed
    return facture;
    }
}
