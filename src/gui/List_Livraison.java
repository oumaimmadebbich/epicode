/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.LivraisonService;
import entities.Livraison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author azizb
 */
public class List_Livraison {
      /**
     * The data as an observable list of Livraisons.
     */
    
    private ObservableList<Livraison> Livraisons=FXCollections.observableArrayList();

    public List_Livraison() {
        
        LivraisonService l=LivraisonService.getInstance();
        Livraisons= l.displayAll();
        System.out.println(Livraisons);
    }
    
    public ObservableList<Livraison> getLivraisons(){
        return Livraisons;
    }
}
