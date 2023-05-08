/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.CommandeSerivce;
import entities.Commande;
import services.LivreurService;
import entities.Livreur;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author azizb
 */
public class List_Commande {
    
     /**
     * The data as an observable list of Livreurs.
     */
    
    private ObservableList<Commande> Livreurs=FXCollections.observableArrayList();

    public List_Commande() {
        
        CommandeSerivce l=new CommandeSerivce();
        Livreurs= l.displayAll();
        System.out.println(Livreurs);
    }
    
    public ObservableList<Commande> getCmd(){
        return Livreurs;
    }
   
}
