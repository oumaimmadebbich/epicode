/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Services.*;
import entites.Categorie;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.MyConnection;

/**
 *
 * @author Imen
 */
public class ProduitService implements IServices <Produit> {
     Connection cnx = MyConnection.getInstance().getCnx(); 
 Statement ste;
    @Override
    public void insert(Produit p) {
        try {
            String req = "INSERT INTO `produit` (`titre`, `prix`,`description`,`id_categorie`,`image`,`quantite`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(6, p.getQuantite());
            ps.setString(5, p.getImage());
            ps.setInt(4, p.getCateg());
            ps.setString(3, p.getDescription());
            ps.setFloat(2, p.getPrix());
            ps.setString(1, p.getTitre());
            ps.executeUpdate();
            System.out.println("ajout effectueé avec sucees");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE `Id_produit` ="+id;
           PreparedStatement pst = cnx.prepareStatement(req);
            //pst.setInt(1, id);
            pst.executeUpdate(req);
            System.out.println("Produit supprimer avec succees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Produit p) {
        try {
            String req = "UPDATE produit SET titre = '"+ p.getTitre()+ "',prix = " +p.getPrix()+ ",description='"+p.getDescription()+"',image='"+p.getImage()+"',quantite="+p.getQuantite()+" WHERE Id_produit =" + p.getId();
            
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.executeUpdate(req);
            System.out.println("Produit mise a jour avec success !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> realAll() {
        List<Produit> categ = new ArrayList<Produit>();
        try {
            ste = cnx.createStatement();
            String req ="SELECT Produit.*, categorie.* FROM Produit  left join categorie  ON categorie.id_categorie=Produit.id_categorie";

            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                 Categorie c = new Categorie(result.getInt(1), result.getString(2));
                
             Produit resultProduit = new Produit(result.getInt(1),  result.getString(2), result.getFloat(3),result.getString(4),result.getString(6),result.getInt(7),c);
                categ.add(resultProduit);
            }
            System.out.println(categ);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return categ;
    }

    @Override
    public Produit readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int nombreproduits() {
int count = 0;
    String query = "SELECT COUNT(*) FROM produit";
    try {
        PreparedStatement ps = cnx.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        count = rs.getInt(1);
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return count;
    }

    public List<Produit> afficherproduit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float totalPrixProduits(List<Produit> produits) {
float total = 0;
    for (Produit p : produits) {
        total += p.getPrix();
        
    }
return total;
    }

    public Map<Integer, String> chercherCategorie() {
Map<Integer, String> categories = new HashMap<>();
    try {
        String sql = "SELECT * FROM categorie;";
        Statement st = cnx.createStatement();
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id_categorie = rs.getInt("id_categorie");
            String nom = rs.getString("Nom_categorie");
            categories.put(id_categorie, nom);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return categories;
    }
    
    public List<Produit> getAllProduct(){
         List<Produit> prod = new ArrayList<>();
        try {
            ste =cnx.createStatement();
            String req = "SELECT * FROM `produit`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Categorie c = new Categorie();
            c.setId(result.getInt("id_categorie"));
             Produit resultProduit = new Produit(result.getInt(1),  result.getString(2), result.getFloat(3),result.getString(4),result.getString(6),result.getInt(7),c);
            prod.add(resultProduit);
        }
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
    }
    public List<Produit> listProduit() {
    List<Produit> produits = new ArrayList<>();
    try {
        PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM produit");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id_produit");
            String titre = rs.getString("titre");
            float prix = rs.getFloat("prix");
            String description = rs.getString("description");
            String image = rs.getString("image");
            int quantite = rs.getInt("quantite");
            int idCategorie = rs.getInt("id_categorie");

            // Retrieve Categorie object for this produit
            Categorie categorie = retrieveCategorie(idCategorie);

            Produit produit = new Produit(id, titre, prix, description, image, quantite, categorie);
            produits.add(produit);
        }
        rs.close();
        stmt.close();
        cnx.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return produits;
}


// Helper function to retrieve Categorie object for a given category ID
private Categorie retrieveCategorie(int id) throws SQLException {
    Categorie categorie = null;
    PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM categorie WHERE id_categorie = ?");
    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        String nom = rs.getString("nom_categorie");
        categorie = new Categorie(id, nom);
    }
    rs.close();
    stmt.close();
    return categorie;
}
}
