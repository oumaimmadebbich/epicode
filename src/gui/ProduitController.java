/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Services.ProduitService;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.itextpdf.text.pdf.PdfPCell;
import entites.Categorie;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import entites.Produit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.connexionDB;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private TextField quantite;
    @FXML
    private ComboBox<String> comb_categ;
    @FXML
    private ImageView imageView;
    @FXML
    private Button ajouter;
    private String pic;
    private Image image1;
    private Connection conx = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<Produit, String> image;
    @FXML
    private TableColumn<Produit, Integer> id_categ;
    @FXML
    private TableView<Produit> table_Prod;
    @FXML
    private TableColumn<Produit, Integer> idP;
    @FXML
    private TableColumn<Produit, String> titreP;
    @FXML
    private TableColumn<Produit, Float> prixP;
    @FXML
    private TableColumn<Produit, String> descriptionP;
    @FXML
    private TableColumn<Produit, Integer> quantiteP;
    private TextField prix1;
    private TextField quantite1;
    private ComboBox<String> comb_categ1;
    private ImageView imageView1;
    @FXML
    private Button pdf;
    private ObservableList<Produit> listProduit;
    @FXML
    private Button codeQr;
    @FXML
    private ImageView codeqrr;
    @FXML
    private HBox chosenhotelCard1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            Connection cnx = connexionDB.getInstance().getConnexion();
            rs = cnx.createStatement().executeQuery("SELECT nom_categorie FROM categorie ");
            // Now add the comboBox addAll statement
            while (rs.next()) {
                comb_categ.getItems().addAll(rs.getString("nom_categorie"));
                //comb_categ1.getItems().addAll(rs.getString("Nom_categorie"));

            }
        } catch (SQLException ex) {

        }

        /**
         * ****************************Affichage*******************************
         */
        ProduitService ps = new ProduitService();

        List<Produit> li = ps.realAll();
        ObservableList<Produit> data = FXCollections.observableArrayList(li);

        idP.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        titreP.setCellValueFactory(
                new PropertyValueFactory<>("titre"));

        prixP.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
        descriptionP.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        /* image.setCellValueFactory(
                new PropertyValueFactory<>("image"));*/
        image.setCellValueFactory(column -> new SimpleObjectProperty(column.getValue().getImage()));
        image.setCellFactory(column -> {
            return new TableCell<Produit, String>() {
                final ImageView imageView = new ImageView();

                {
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        imageView.setImage(null);
                        setGraphic(null);
                    } else {
                        File file = new File(item);
                        Image image = new Image(file.toURI().toString(), 100, 100, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    }
                }
            };
        });
        quantiteP.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));
        id_categ.setCellValueFactory(
                new PropertyValueFactory<>("categorie"));

        System.out.println(data);
        table_Prod.setItems(data);

    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String titrePp = titre.getText();
        String prixPp = prix.getText();
        String descriptionPp = description.getText();
        String quantitePp = quantite.getText();

        if (titrePp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("titre doit etre saisi");
            titre.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (quantitePp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("quantite doit etre saisi");
            quantite.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (descriptionPp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setContentText("Description doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (prixPp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            prix.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setContentText("Prix doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            try {
                Connection cnx = connexionDB.getInstance().getConnexion();
                ResultSet rs6 = null;
                String v = String.valueOf(comb_categ.getValue());
                rs6 = cnx.createStatement().executeQuery("SELECT id FROM categorie where nom_categorie='" + v + "'");

                rs6.next();
                int id = rs6.getInt("id");

                int quantitep = Integer.parseInt(quantite.getText());
                float prixp = Float.parseFloat(prix.getText());

                // Open a file chooser to let the user select an image file
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choisir une image");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
                );
                File selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {
                    // Read the bytes of the selected image file
                    String imagePath = selectedFile.toPath().toString();

                    /**
                     * *
                     */
                    pic = (selectedFile.toURI().toString());
                    image1 = new Image(pic);
                    imageView.setImage(image1);
                    /**
                     * *****
                     */
                    Produit p = new Produit(titrePp, prixp, descriptionPp, imagePath, quantitep, id);

                    ProduitService ps = new ProduitService();
                    ps.insert(p);
                    notiff();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit  inséré avec succès!");
                    alert.showAndWait();

                    /**
                     * *************************
                     */
                    List<Produit> li = ps.realAll();

                    ObservableList<Produit> data = FXCollections.observableArrayList(li);

                    idP.setCellValueFactory(
                            new PropertyValueFactory<>("id"));
                    titreP.setCellValueFactory(
                            new PropertyValueFactory<>("titre"));

                    prixP.setCellValueFactory(
                            new PropertyValueFactory<>("prix"));
                    descriptionP.setCellValueFactory(
                            new PropertyValueFactory<>("description"));

                    /*image.setCellValueFactory(
                new PropertyValueFactory<>("image"));*/
                    image.setCellFactory(column -> {
                        return new TableCell<Produit, String>() {
                            final ImageView imageView = new ImageView();

                            {
                                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            }

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item == null || empty) {
                                    imageView.setImage(null);
                                    setGraphic(null);
                                } else {
                                    File file = new File(item);
                                    Image image = new Image(file.toURI().toString(), 100, 100, true, true);
                                    imageView.setImage(image);
                                    setGraphic(imageView);
                                }
                            }
                        };
                    });
                    quantiteP.setCellValueFactory(
                            new PropertyValueFactory<>("quantite"));
                    id_categ.setCellValueFactory(
                            new PropertyValueFactory<>("categorie"));

                    table_Prod.setItems(data);

                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("ID  et prix Produit doivent etre des nombres");
                alert.setTitle("Probleme");
                alert.setHeaderText(null);
                alert.showAndWait();

            }
            /*catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la lecture de l'image sélectionnée");
            alert.showAndWait();
        }*/
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {

        int myIndex = table_Prod.getSelectionModel().getSelectedIndex();
        System.out.println("index :" + myIndex);
        int idS = Integer.parseInt(String.valueOf(table_Prod.getItems().get(myIndex).getId()));
        ProduitService ps = new ProduitService();
        ps.delete(idS);
        envoyeremail();

        /**
         * ***
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Supprimé avec succée !");
        alert.show();

        /**
         * *********************
         */
        List<Produit> li = ps.realAll();
        ObservableList<Produit> data = FXCollections.observableArrayList(li);

        idP.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        titreP.setCellValueFactory(
                new PropertyValueFactory<>("titre"));

        prixP.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
        descriptionP.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
        quantiteP.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));
        id_categ.setCellValueFactory(
                new PropertyValueFactory<>("categorie"));

        table_Prod.setItems(data);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {

        String titrePp = titre.getText();
        String prixPp = prix.getText();
        String descriptionPp = description.getText();
        String quantitePp = quantite.getText();

        int myIndex = table_Prod.getSelectionModel().getSelectedIndex();
        System.out.println("index :" + myIndex);
        int idS = Integer.parseInt(String.valueOf(table_Prod.getItems().get(myIndex).getId()));

        if (titrePp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("titre doit etre saisi");
            titre.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (quantitePp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("quantite doit etre saisi");
            quantite.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (descriptionPp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setContentText("Description doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (prixPp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            prix.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            alert.setContentText("Prix doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            try {
                Connection cnx = connexionDB.getInstance().getConnexion();
                ResultSet rs6 = null;
                String v = String.valueOf(comb_categ.getValue());
                rs6 = cnx.createStatement().executeQuery("SELECT id FROM categorie where nom_categorie='" + v + "'");

                rs6.next();
                int id = rs6.getInt("id");

                int quantitep = Integer.parseInt(quantite.getText());
                float prixp = Float.parseFloat(prix.getText());

                // Open a file chooser to let the user select an image file
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choisir une image");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
                );
                File selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {
                    // Read the bytes of the selected image file
                    String imagePath1 = selectedFile.toPath().toString();

                    // String imageBytes = Files.readAllBytes(selectedFile.toPath()).toString();
                    /**
                     * *
                     */
                    pic = (selectedFile.toURI().toString());
                    image1 = new Image(pic);
                    imageView.setImage(image1);
                    /**
                     * *****
                     */
                    Produit p = new Produit(idS, titrePp, prixp, descriptionPp, imagePath1, quantitep, id);

                    ProduitService ps = new ProduitService();
                    ps.update(p);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit  update avec succès!");
                    alert.showAndWait();

                    /**
                     * *************************
                     */
                    List<Produit> li = ps.realAll();
                    ObservableList<Produit> data = FXCollections.observableArrayList(li);

                    idP.setCellValueFactory(
                            new PropertyValueFactory<>("id"));
                    titreP.setCellValueFactory(
                            new PropertyValueFactory<>("titre"));

                    prixP.setCellValueFactory(
                            new PropertyValueFactory<>("prix"));
                    descriptionP.setCellValueFactory(
                            new PropertyValueFactory<>("description"));

                    image.setCellValueFactory(
                            new PropertyValueFactory<>("image"));
                    quantiteP.setCellValueFactory(
                            new PropertyValueFactory<>("quantite"));
                    id_categ.setCellValueFactory(
                            new PropertyValueFactory<>("categorie"));

                    table_Prod.setItems(data);

                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("ID  et prix Produit doivent etre des nombres");
                alert.setTitle("Probleme");
                alert.setHeaderText(null);
                alert.showAndWait();
                /*} catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la lecture de l'image sélectionnée");
            alert.showAndWait();*/
            }
        }
    }

    @FXML
    private void gestion_categorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));

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

    @FXML
    private void stat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Produitstat.fxml"));

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

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("table_produit.pdf"));
        document.open();

        PdfPTable pdfTable = new PdfPTable(7);

        // Add table headers
        pdfTable.addCell(new Phrase("Id"));
        pdfTable.addCell(new Phrase("Titre"));
        pdfTable.addCell(new Phrase("Prix"));
        pdfTable.addCell(new Phrase("Description"));
        pdfTable.addCell(new Phrase("Image"));
        pdfTable.addCell(new Phrase("Quantite"));
        pdfTable.addCell(new Phrase("Categorie"));

        // Get the list of products from the ProduitServices class
        ProduitService produitServices = new ProduitService();
        List<Produit> listProduit = produitServices.listProduit();

        // Add table rows
        for (Produit produit : listProduit) {
            pdfTable.addCell(new Phrase(Integer.toString(produit.getId())));
            pdfTable.addCell(new Phrase(produit.getTitre()));
            pdfTable.addCell(new Phrase(Float.toString(produit.getPrix())));
            pdfTable.addCell(new Phrase(produit.getDescription()));
            //pdfTable.addCell(new Phrase(produit.getImage()));
            // Image image=new Image(ImageDataFactory.create(produit.getImage()));

            /* com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(produit.getImage());
       pdfTable.addCell(image);*/
            // Check if the image is valid before adding it to the table
            com.itextpdf.text.Image image = null;
            if (produit.getImage() != null) {
                try {
                    image = com.itextpdf.text.Image.getInstance(produit.getImage());
                } catch (BadElementException | IOException ex) {
                    // Display an error message if the image cannot be added to the table
                    System.err.println("Error adding image to PDF table: " + ex.getMessage());
                }
            }
            pdfTable.addCell(image);

            pdfTable.addCell(new Phrase(Integer.toString(produit.getQuantite())));
            pdfTable.addCell(new Phrase(produit.getCategorie().getNom()));
        }

        document.add(pdfTable);
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler table_produit.pdf");

    }

    @FXML
    private void qrcode(ActionEvent event) {
        Produit p = table_Prod.getSelectionModel().getSelectedItem();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "titre: " + p.getTitre() + "\n" + "prix : " + p.getPrix() + "\n" + "description : " + p.getDescription();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

            codeqrr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        } catch (WriterException ex) {

        }
    }

    @FXML
    private void excel(ActionEvent event) {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\trabe\\Desktop\\Produit.csv"), "UTF-8"));
            ProduitService cr = new ProduitService();;

            List<Produit> metiers = cr.realAll();
            writer.write("ID   : Prix :Quantite :Titre    :Description\n");
            for (Produit obj : metiers) {
                int id = obj.getId();
writer.write(Integer.toString(id));

                writer.write("    :");
                Float prix=obj.getPrix();
                writer.write(Float.toString(prix));
                writer.write("    :");
                int quantite=obj.getQuantite();
                writer.write(Integer.toString(quantite));
                writer.write("    :");
                
                writer.write(obj.getTitre());
                writer.write("    :");

                writer.write(obj.getDescription());
                writer.write("  :");
                

                writer.write("\n");

            }
            writer.flush();
            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EXCEL ");

            alert.setHeaderText("EXCEL");
            alert.setContentText("Enregistrement effectué avec succès!");

            alert.showAndWait();
        } catch (Exception e) {
            System.out.println("Failed to send message: " + e.getMessage());
        }

    }

    private void notiff() {
        ProduitService sv = new ProduitService();
        Produit RV = new Produit();
        String Titre = titre.getText();
        RV.setTitre(Titre);
        tray.notification.TrayNotification notification = new tray.notification.TrayNotification();
        AnimationType type = AnimationType.POPUP;
        notification.setAnimationType(type);
        notification.setTitle("Bienvenue à reUsed");
        notification.setMessage("Titre " + RV.getTitre() + " a été effectué avec succès.");
        notification.setNotificationType(NotificationType.INFORMATION);
        notification.showAndDismiss(Duration.millis(2000));

    }

    public void envoyeremail() {
        String to = "oumaima.debbich@esprit.tn";
        String from = "moula.mohamedali@esprit.tn";
        String password = "223JMT2830";

        String host = "smtp.gmail.com";
        String port = "587";

        // Set up properties for the SMTP connection
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a new Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oumaima.debbich@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Produit  Valider");
            message.setText("Votre produit  est supprimé  ");

            // Send the message
            Transport.send(message);

            System.out.println("Message sent successfully!");

        } catch (MessagingException e) {
            System.out.println("Failed to send message: " + e.getMessage());
        }

    }

    @FXML
    private void retour(ActionEvent event) {
                 try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/dash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
