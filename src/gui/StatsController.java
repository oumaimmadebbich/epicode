/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.DemandeService;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart statistique;
    @FXML
    private PieChart statistique2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    int total = 0;
DemandeService ds = new DemandeService();
OffreService os = new OffreService();


int encours = ds.nbEncours();
int traite = ds.nbTraité();
total = encours + traite;

double prcntFeed = (encours * 100) / total;
double prcntRec = (traite * 100) / total;

ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
        new PieChart.Data("En cours : " +ds.nbEncours() + "reclamation", encours),
        new PieChart.Data("Traité : " + ds.nbTraité() + "reclamation",traite)
);


statistique.setTitle("Etat des demandes");
statistique.setAnimated(true);
statistique.setData(list);


 //Récupération des titres et du nombre de demandes pour chaque offre
List<Object> l = os.getTitresEtDemandes();
List<String> titresOffres = (List<String>) l.get(0);
List<Integer> nbDemandesOffres = (List<Integer>) l.get(1);

// Création d'une liste de données pour la deuxième PieChart
ObservableList<PieChart.Data> list2 = FXCollections.observableArrayList();
for (int i = 0; i < titresOffres.size(); i++) {
    String titreOffre = titresOffres.get(i);
    int nbDemandes = nbDemandesOffres.get(i);
    list2.add(new PieChart.Data(titreOffre + " (" + nbDemandes + ")", nbDemandes));
}

// Création de la deuxième PieChart et ajout des données

statistique2.setTitle("Nbre de Demandes par offre");
statistique2.setAnimated(true);
statistique2.setData(list2);

    }    
    }    
    

