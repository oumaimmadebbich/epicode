/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Event;
import services.EventInterface;
import services.EventService;




/**
 * FXML Controller class
 *
 * @author ZC-Lenovo
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField NomText;
    private TextField TextPrenom;
    @FXML
    private TextArea AllText;
    
    @FXML
    private Button Bimage;
    @FXML
    private TextField imageE;
    @FXML
    private ImageView ImageView;
    @FXML
    private DatePicker TimeEvent;
    @FXML
    private TextField Organisation;
    @FXML
    private TableView<Event> EventTable;
    @FXML
    private TableColumn<Event,String> idCol;
    @FXML
    private TableColumn<Event, String> TitleCol;
    @FXML
    private TableColumn<Event, String> DescriptionCol;
    @FXML
    private TableColumn<Event, String> TimeCol;
    @FXML
    private TableColumn<Event, String> ImageCol;
    @FXML
    private TableColumn<Event, String> OrganisationCol;
    Event EV=new Event();
    @FXML
    private TableColumn<Event, String> editCol;
    private int v=0;
    @FXML
    private Text ERROR1;
    @FXML
    private Text ERROR2;
    @FXML
    private Text ERROR3;
    @FXML
    private Text ERROR4;
    @FXML
    private Text ERROR5;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         loadDate();
    }    

    @FXML
    private void AddAction(ActionEvent event) {
        
        EventInterface ps = new EventService();
 LocalDate d=LocalDate.now();
          Date t=convertToDateUsingDate(d);
              Date MYDate = Date.valueOf(TimeEvent.getValue().toString());
          if(NomText.getText().length()==0){ERROR1.setText("Missing Title");}else
                           
                           if((AllText.getText().length()==0)&&(AllText.getText().length()<10)){ERROR2.setText("Missing or Needing more caracteres Description");}else
                               
                               if(imageE.getText().length()==0){ERROR3.setText("Missing Image");}else
                                   
                                   if((Organisation.getText().length()==0)&&(Organisation.getText().length()<3)){ERROR5.setText("Missing or needing more caracteres organisation");}else
                                       if(t.getTime()>MYDate.getTime()){ERROR4.setText("Please select date in future or today");}
                                       else 
                                       {  
                    ERROR1.setText("");
                    ERROR2.setText("");
                    ERROR3.setText("");
                    ERROR4.setText("");
                    ERROR5.setText("");
       String title=NomText.getText();
       String organisation=Organisation.getText();
       String desrcription=AllText.getText();
       
       
       Event E=new Event();
      
       E.setDescriptionEvent(desrcription);
       
       E.setTimeEvent(MYDate);
       E.setTitleEvent(title);
       E.setOrganisation(organisation);
       E.setImageEvent(imageE.getText());
   
       
       ps.addEvent(E);
   Date v=new Date(0000-00-00);
       this.setTextField("", "", "", v, "");
       refreshTable();
                                       }
       
    }

    @FXML
    private void BrowseImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\Offre+Demande\\public\\uploads\\"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            ImageView.setImage(img);
            /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
             imageE.setText(x+".jpg");
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    }
    private void loadDate() {
         EventInterface ps = new EventService();
        
        ObservableList<Event> olp = FXCollections.observableArrayList(ps.fetchEvents());
        idCol.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("titleEvent"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("descriptionEvent"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("timeEvent"));
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("imageEvent"));
        OrganisationCol.setCellValueFactory(new PropertyValueFactory<>("Organisation"));
        
        //add cell of button edit 
         Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFoctory = (TableColumn<Event, String> param) -> {
            // make cell containing buttons
            final TableCell<Event, String> cell = new TableCell<Event, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                           
                                EV = EventTable.getSelectionModel().getSelectedItem();
                               ps.delete(EV.getIdEvent());
                              refreshTable();
                                
                            
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            v++;
                            
                                if(v%2!=0)
                                {
                            EV= EventTable.getSelectionModel().getSelectedItem();
                            
                            
                            
                            setTextField( EV.getTitleEvent(),EV.getDescriptionEvent(),
                                    EV.getImageEvent(),EV.getTimeEvent(),EV.getOrganisation());
                                }
                                else
                                { LocalDate d=LocalDate.now();
          Date t=convertToDateUsingDate(d);
              Date MYDate = Date.valueOf(TimeEvent.getValue().toString());
                                    
                                     if(NomText.getText().length()==0){ERROR1.setText("Missing Title");}else
                           
                           if((AllText.getText().length()==0)&&(AllText.getText().length()<10)){ERROR2.setText("Missing or Needing more caracteres Description");}else
                               
                               if(imageE.getText().length()==0){ERROR3.setText("Missing Image");}else
                                   
                                   if((Organisation.getText().length()==0)&&(Organisation.getText().length()<3)){ERROR5.setText("Missing or needing more caracteres organisation");}else
                                       if(t.getTime()>MYDate.getTime()){ERROR4.setText("Please select date in future or today");}
                                       else 
                                       {  
                    ERROR1.setText("");
                    ERROR2.setText("");
                    ERROR3.setText("");
                    ERROR4.setText("");
                    ERROR5.setText("");
                                           
                                    String title=NomText.getText();
       String organisation=Organisation.getText();
       String desrcription=AllText.getText();
       
       
       EV.setDescriptionEvent(desrcription);
      
       EV.setTimeEvent(MYDate);
       EV.setTitleEvent(title);
       EV.setOrganisation(organisation);
       EV.setImageEvent(imageE.getText());
                                    
       
                                    ps.updateEvent(EV);
                                       Date v=new Date(0000-00-00);
                                       setTextField("","","",v,"");
                  
                                refreshTable();
                                       }
                                        }
                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                      //  HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                      //  HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         EventTable.setItems(olp);
         
         
    }
      public void refreshTable() {
        EventInterface ps = new EventService();
            ObservableList<Event> olp = FXCollections.observableArrayList(ps.fetchEvents());
            EventTable.setItems(olp);
            
        } 
      void setTextField( String title, String description,  String image, Date time,String organisation) {

        
        NomText.setText(title);
        AllText.setText(description);
        imageE.setText(image);
        LocalDate date = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(time) );
 
        TimeEvent.setValue(date);
        Organisation.setText(organisation);

    }
     

       public static Date convertToDateUsingDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
}

    

