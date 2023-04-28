/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.ProduitService;
import entites.Categorie;
import entites.Produit;
import entites.ProduitRemise;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oumaima debbich
 */
public class ProduitstatController implements Initializable {

   @FXML
    private Label totale;
    @FXML
    private Label average;
    @FXML
    private PieChart pie;
    @FXML
    private TableView <ProduitRemise> remisetab;
    @FXML
    private TableColumn<ProduitRemise, String> nomProdd;
    @FXML
    private TableColumn<ProduitRemise, Float> prixprodd;
    @FXML
    private TableColumn<ProduitRemise, Integer> pourprodd;
    @FXML
    private TableColumn<ProduitRemise, Float> prixRemise;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
    ProduitService cr = new ProduitService();
    Double total = 0.0;
    Double avg = 0.0;
    // Total number of products
    int totalProducts = cr.nombreproduits();
    List<Produit> produits = cr.getAllProduct(); 
    // Total price of all products
    float totalPrice = cr.totalPrixProduits(produits);

    // Average price of products
    float avgPrice = (totalProducts > 0) ? totalPrice / totalProducts : 0;

    // Set the labels
    totale.setText(String.valueOf(totalProducts));
    average.setText(String.valueOf(avgPrice));

    // Pie chart data
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

    // Count number of products per categorie
    Map<Integer, Integer> countByCategorie = new HashMap<>();
    Map<Integer, String> categories = cr.chercherCategorie();
    for (Produit produit : produits) {
        int categorieId = produit.getCategorie().getId();
        if (!countByCategorie.containsKey(categorieId)) {
            countByCategorie.put(categorieId, 0);
        }
        countByCategorie.put(categorieId, countByCategorie.get(categorieId) + 1);
    }
    //la meilleure categorie
   int idCategoriePu= categoriePlusUtiliser(countByCategorie);
        System.out.println("id categorie="+idCategoriePu);
    List<ProduitRemise> produitsCategorie=produits.stream().filter(p->p.getCategorie().getId()==idCategoriePu)
            .map(p->new ProduitRemise(p.getTitre(),p.getPrix(),20,p.getPrix()-p.getPrix()*0.2F))
            .collect(Collectors.toList());
    afficherProdRemise(produitsCategorie);
    pie.setData(null);
        System.out.println(countByCategorie);
    // Add data to the pie chart
    for (Map.Entry<Integer, Integer> entry : countByCategorie.entrySet()) {
        int categorieId = entry.getKey();
        int count = entry.getValue();
        String categorieName = categories.get(categorieId);
        data.add(new PieChart.Data(categorieName, count));
    }

    pie.setData(data);
}

    private int categoriePlusUtiliser(Map<Integer, Integer> countByCategorie) {
int max= Collections.max(countByCategorie.values());
   return countByCategorie.entrySet().stream()
           .filter(x->x.getValue()==max)
           .map(Map.Entry::getKey)
           .collect(Collectors.toList()).get(0);
    }

    private void afficherProdRemise(List<ProduitRemise> produitsCategorie) {
 ObservableList<ProduitRemise> data = FXCollections.observableArrayList(produitsCategorie);
                  

      nomProdd.setCellValueFactory(x->new SimpleStringProperty(x.getValue().getNomProduit()));
      //nomProdd.setCellValueFactory(
            //  new PropertyValueFactory<>("nom"));
      prixprodd.setCellValueFactory(x->new SimpleFloatProperty(x.getValue().getPrix()).asObject());

     // prixprodd.setCellValueFactory(
               // new PropertyValueFactory<>("prix"));
       pourprodd.setCellValueFactory(x->new SimpleIntegerProperty(x.getValue().getRemise()).asObject());

      // pourprodd.setCellValueFactory(
               // new PropertyValueFactory<>("pourcentage remise"));
             prixRemise.setCellValueFactory(x->new SimpleFloatProperty(x.getValue().getPrixRemise()).asObject());

 //prixRemise.setCellValueFactory(
               // new PropertyValueFactory<>("prix remise"));
 
       
     
        
                System.out.println(produitsCategorie); 
        remisetab.setItems(data);
    }    


    

    @FXML
    private void gestion_produit(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
        
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
           
        }
    }
          

    
    
}
