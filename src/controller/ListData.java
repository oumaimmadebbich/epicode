/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.LivreurService;
import entity.Livreur;
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
public class ListData {
    
     /**
     * The data as an observable list of Livreurs.
     */
    
    private ObservableList<Livreur> Livreurs=FXCollections.observableArrayList();

    public ListData() {
        
        LivreurService l=LivreurService.getInstance();
        Livreurs= l.displayAll();
        System.out.println(Livreurs);
    }
    
    public ObservableList<Livreur> getLivreurs(){
        return Livreurs;
    }
   
}
