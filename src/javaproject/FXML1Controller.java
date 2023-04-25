/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import com.google.zxing.WriterException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Client;


import models.Event;
import services.EventInterface;
import services.EventService;
import services.ParticipationInterface;
import services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author ZC-Lenovo
 */
public class FXML1Controller implements Initializable {
    public  int p=0;
    
    @FXML
    private ImageView ImageV2;
    @FXML
    private Text nbrP;
    @FXML
    private Button ButtonP;
    @FXML
    private Button ButtonS;
    @FXML
    private Text textT;
    @FXML
    private Text TextD;
    @FXML
    private Text TextO;
    @FXML
    private Text FlowEvent;
   private Button Switch1;
    @FXML
   private Button Switch2;
   private boolean mouseClicked = false;
    @FXML
    
   private AnchorPane AnchorE;
    
   EventInterface ps = new EventService();
   List<Event> EL=ps.fetchEvents();
   int i=0;
   int m=EL.size();
   ParticipationInterface pl=new ParticipationService();
   
    Event EV=new Event();
    @FXML
    private MenuItem VersP;
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Load();
    }    
    private void Load()
    {   try {
         FontAwesomeIconView partIcon = new FontAwesomeIconView(FontAwesomeIcon.GROUP);
                        
        partIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#46ADD0;"
                       
                        );
        
        AnchorE.getChildren().add(partIcon);

// Set the position of the icon within the AnchorPane
AnchorPane.setLeftAnchor(partIcon, 43.0);
AnchorPane.setTopAnchor(partIcon, 350.0);
        Event Es=EL.get(0);
        p=Es.getIdEvent();
        int k=pl.Count(Es.getIdEvent());
         String r=String.valueOf(k);
        nbrP.setText(r);
        textT.setText(Es.getTitleEvent());
        TextO.setText(Es.getOrganisation());
        TextD.setText(Es.getTimeEvent().toString());
        FlowEvent.setAccessibleText(Es.getDescriptionEvent());
        String x=Es.getImageEvent();
        String DBPath = "C:\\xampp\\htdocs\\Offre+Demande\\public\\uploads\\" + x;
        File file = new File(DBPath);
        Image img = new Image(file.toURI().toURL().toString());
        ImageV2.setImage(img);
        
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Participate(ActionEvent event) throws IOException, WriterException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QrCode.fxml"));
        Client c=new Client(1,"Nouira","Amine","Medamine.nouira@esprit.tn","amineee",52531317,100);
        QrCodeController controller = new QrCodeController();
        controller.setParam1(c.getId());
             controller.setParam(p);
        
            loader.setController(controller);
            Parent parent = loader.load();
             
            
            
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        
            
            
    }

    @FXML
    private void Share(ActionEvent event) {
    }

    public FXML1Controller() {
    }
    

    @FXML
    private void SwapR(ActionEvent event) throws MalformedURLException {
        
        if(i!=m)
        {i++;
         Event E=EL.get(i);
         p=E.getIdEvent();
         int k=pl.Count(E.getIdEvent());
             
                  String x=E.getImageEvent();
            String DBPath = "C:\\xampp\\htdocs\\Offre+Demande\\public\\uploads\\" + x;
            File file = new File(DBPath);
            Image img = new Image(file.toURI().toURL().toString());
            ImageV2.setImage(img);
            String r=String.valueOf(k);
            nbrP.setText(r);
           FlowEvent.setText(E.getDescriptionEvent());
            textT.setText(E.getTitleEvent());
            TextO.setText(E.getOrganisation());
            TextD.setText(E.getTimeEvent().toString());
        }
            
        
        
    }

    @FXML
    private void SwapL(ActionEvent event) throws MalformedURLException {
         if(i!=0)
        {i--;
         Event E=EL.get(i);
         p=E.getIdEvent();
         int k=pl.Count(E.getIdEvent());
         String r=String.valueOf(k);
             
                  String x=E.getImageEvent();
            String DBPath = "C:\\xampp\\htdocs\\Offre+Demande\\public\\uploads\\" + x;
            File file = new File(DBPath);
            Image img = new Image(file.toURI().toURL().toString());
            ImageV2.setImage(img);
            nbrP.setText(r);
            FlowEvent.setText(E.getDescriptionEvent());
            textT.setText(E.getTitleEvent());
            TextO.setText(E.getOrganisation());
            TextD.setText(E.getTimeEvent().toString());
        }
    }

    @FXML
    private void ChangeToP(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Tickerfxml.fxml"));
        
        
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root, 300, 250);
        
        scene.getStylesheets().add(getClass().getResource("MyText.css").toExternalForm());
        
        Stage primaryStage=new Stage();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
    
}
