/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import entities.Event;
import entities.Participation;
import services.EventInterface;
import services.EventService;
import services.ParticipationInterface;
import services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author ZC-Lenovo
 */
public class TickerfxmlController implements Initializable {

    private Pagination Pag;
    ParticipationInterface ps = new ParticipationService();
   List<Participation> EL=ps.fetchParticiation();
   EventInterface pv = new EventService();
   Event EV=pv.getEV(EL.get(0).getIdEv());
   
    @FXML
    private AnchorPane anchor;
    @FXML
    private ToggleButton IMPRIME;
     WebView webview;
    @FXML
    private MenuItem VersEv;
    @FXML
    private MenuItem VersP;

    /**
     * Initializes the controller class.
     */
    public WebView addT(int pageIndex) throws WriterException, IOException
    { webview=new WebView();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
    String year=sdf.format(EL.get(pageIndex).getDateEv());
    Format f = new SimpleDateFormat("EEEE");  
    String str = f.format(EL.get(pageIndex).getDateEv());
     EV=pv.getEV(EL.get(pageIndex).getIdEv());
      String DBPath =   EV.getImageEvent();
        System.out.println(DBPath);
        String qrCodeData = "**** Prenom :"+EL.get(pageIndex).getPrenom()+"**** /n Nom :"
                + EL.get(pageIndex).getNom()+"/n **** ID Client:"+EL.get(pageIndex).getIdC()+"****";
        
            String filePath = "codeqrr.png";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 150,150);
            
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(filePath));
            
           Image  image = new Image(new File(filePath).toURI().toString());
           String imageData = "";
try {
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ImageIO.write(bImage, "jpg", bos);
    byte[] data = bos.toByteArray();
    imageData = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(data);
} catch (IOException e) {
    e.printStackTrace();
}

      
       
    
        webview.getEngine().loadContent("<html>" +
                "<head>" +
                "<style>" +
                "@import url('https://fonts.googleapis.com/css2?family=Staatliches&display=swap');" +
                "@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');" +
                "@import url(\"https://fonts.googleapis.com/css2?family=Staatliches&display=swap\");\n" +
"@import url(\"https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap\");\n" +
"\n" +
"* {\n" +
"	margin: 0;\n" +
"	padding: 0;\n" +
"	box-sizing: border-box;\n" +
"}\n" +
"\n" +
"body,\n" +
"html {\n" +
"	height: 100vh;\n" +
"	display: grid;\n" +
"	font-family: \"Staatliches\", cursive;\n" +
"	background: #d83565;\n" +
"	color: black;\n" +
"	font-size: 14px;\n" +
"	letter-spacing: 0.1em;\n" +
"}\n" +
"\n" +
".ticket {\n" +
"	margin: auto;\n" +
"	display: flex;\n" +
"	background: white;\n" +
"	box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;\n" +
"}\n" +
"\n" +
".left {\n" +
"	display: flex;\n" +
"}\n" +
"\n" +
".image {\n" + 
"	height: 250px;\n" +
"	width: 250px;\n" +
"	background-size: contain;\n" +
"	opacity: 0.85;\n" +
"}\n" +
"\n" +
".admit-one {\n" +
"	position: absolute;\n" +
"	color: darkgray;\n" +
"	height: 250px;\n" +
"	padding: 0 10px;\n" +
"	letter-spacing: 0.15em;\n" +
"	display: flex;\n" +
"	text-align: center;\n" +
"	justify-content: space-around;\n" +
"	writing-mode: vertical-rl;\n" +
"	transform: rotate(-180deg);\n" +
"}\n" +
"\n" +
".admit-one span:nth-child(2) {\n" +
"	color: white;\n" +
"	font-weight: 700;\n" +
"}\n" +
"\n" +
".left .ticket-number {\n" +
"	height: 250px;\n" +
"	width: 250px;\n" +
"	display: flex;\n" +
"	justify-content: flex-end;\n" +
"	align-items: flex-end;\n" +
"	padding: 5px;\n" +
"}\n" +
"\n" +
".ticket-info {\n" +
"	padding: 10px 30px;\n" +
"	display: flex;\n" +
"	flex-direction: column;\n" +
"	text-align: center;\n" +
"	justify-content: space-between;\n" +
"	align-items: center;\n" +
"}\n" +
"\n" +
".date {\n" +
"	border-top: 1px solid gray;\n" +
"	border-bottom: 1px solid gray;\n" +
"	padding: 5px 0;\n" +
"	font-weight: 700;\n" +
"	display: flex;\n" +
"	align-items: center;\n" +
"	justify-content: space-around;\n" +
"}\n" +
"\n" +
".date span {\n" +
"	width: 100px;\n" +
"}\n" +
"\n" +
".date span:first-child {\n" +
"	text-align: left;\n" +
"}\n" +
"\n" +
".date span:last-child {\n" +
"	text-align: right;\n" +
"}\n" +
"\n" +
".date .june-29 {\n" +
"	color: #d83565;\n" +
"	font-size: 20px;\n" +
"}\n" +
"\n" +
".show-name {\n" +
"	font-size: 32px;\n" +
"	font-family: \"Nanum Pen Script\", cursive;\n" +
"	color: #d83565;\n" +
"}\n" +
"\n" +
".show-name h1 {\n" +
"	font-size: 48px;\n" +
"	font-weight: 700;\n" +
"	letter-spacing: 0.1em;\n" +
"	color: #4a437e;\n" +
"}\n" +
"\n" +
".time {\n" +
"	padding: 10px 0;\n" +
"	color: #4a437e;\n" +
"	text-align: center;\n" +
"	display: flex;\n" +
"	flex-direction: column;\n" +
"	gap: 10px;\n" +
"	font-weight: 700;\n" +
"}\n" +
"\n" +
".time span {\n" +
"	font-weight: 400;\n" +
"	color: gray;\n" +
"}\n" +
"\n" +
".left .time {\n" +
"	font-size: 16px;\n" +
"}\n" +
"\n" +
"\n" +
".location {\n" +
"	display: flex;\n" +
"	justify-content: space-around;\n" +
"	align-items: center;\n" +
"	width: 100%;\n" +
"	padding-top: 8px;\n" +
"	border-top: 1px solid gray;\n" +
"}\n" +
"\n" +
".location .separator {\n" +
"	font-size: 20px;\n" +
"}\n" +
"\n" +
".right {\n" +
"	width: 180px;\n" +
"	border-left: 1px dashed #404040;\n" +
"}\n" +
"\n" +
".right .admit-one {\n" +
"	color: darkgray;\n" +
"}\n" +
"\n" +
".right .admit-one span:nth-child(2) {\n" +
"	color: gray;\n" +
"}\n" +
"\n" +
".right .right-info-container {\n" +
"	height: 250px;\n" +
"	padding: 10px 10px 10px 35px;\n" +
"	display: flex;\n" +
"	flex-direction: column;\n" +
"	justify-content: space-around;\n" +
"	align-items: center;\n" +
"}\n" +
"\n" +
".right .show-name h1 {\n" +
"	font-size: 18px;\n" +
"}\n" +
"\n" +
".barcode {\n" +
"	height: 100px;\n" +
"}\n" +
"\n" +
".barcode img {\n" +
"	height: 100%;\n" +
"}\n" +
"\n" +
".right .ticket-number {\n" +
"	color: gray;\n" +
"}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<link rel=\"stylesheet\"\n" +
"            href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\"/>\n" +
"\n" +
"<div class=\"ticket\">\n" +
"	<div class=\"left\">\n" +
"		<div class=\"image\">\n" +
                "<img class=\"image\" src=file:///C:/xampp/htdocs/Offre+Demande/public/uploads/"+DBPath+">\n"+
"			<p class=\"admit-one\">\n" +
"				<span>ADMIT ONE</span>\n" +
"				<span>ADMIT ONE</span>\n" +
"				<span>ADMIT ONE</span>\n" +
"			</p>\n" +
"			<div class=\"ticket-number\">\n" +
"				<p>\n" +
"					#"+EL.get(pageIndex).getId()+"\n" +
"				</p>\n" +
"			</div>\n" +
"		</div>\n" +
"		<div class=\"ticket-info\">\n" +
"			<p class=\"date\">\n" +
"				<span>"+str+"</span>\n" +
"				<span class=\"june-29\">"+EL.get(pageIndex).getDateEv()+"</span>\n" +
"				<span>"+year+"</span>\n" +
"			</p>\n" +
"			<div class=\"show-name\">\n" +
"				<h1>"+ EL.get(pageIndex).getNomEv()+"</h1>\n" +
"				<h2>Reused</h2>\n" +
"			</div>\n" +
"			<div class=\"time\">\n" +
"				<p>8:00 PM <span>TO</span> 11:00 PM</p>\n" +
"				<p>DOORS <span>@</span> 7:00 PM</p>\n" +
"			</div>\n" +
"			<p class=\"location\"><span>East High School</span>\n" +
"				<span class=\"separator\"><i class=\"far fa-smile\"></i></span><span>Salt Lake City, Utah</span>\n" +
"			</p>\n" +
"		</div>\n" +
"	</div>\n" +
"	<div class=\"right\">\n" +
"		<p class=\"admit-one\">\n" +
"			<span>ADMIT ONE</span>\n" +
"			<span>ADMIT ONE</span>\n" +
"			<span>ADMIT ONE</span>\n" +
"		</p>\n" +
"		<div class=\"right-info-container\">\n" +
"			<div class=\"show-name\">\n" +
"				<h1>SOUR Prom</h1>\n" +
"			</div>\n" +
"			<div class=\"time\">\n" +
"				<p>8:00 PM <span>TO</span> 11:00 PM</p>\n" +
"				<p>DOORS <span>@</span> 7:00 PM</p>\n" +
"			</div>\n" +
"			<div class=\"barcode\">\n" +
"				<img src="+imageData+" alt=\"QR code\">\n" +
"			</div>\n" +
"			<p class=\"ticket-number\">\n" +
"				#"+EL.get(pageIndex).getId()+"\n" +
"			</p>\n" +
"		</div>\n" +
"	</div>\n" +
"</div>" +
                "</body>" +
                "</html>");
    return webview;}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ParticipationInterface p = new ParticipationService();
   List<Participation> E=p.fetchParticiation();
  
   Pag=new Pagination(E.size(),0);
   Pag.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
   Pag.setMaxPageIndicatorCount(E.size());
   Pag.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                try {
                    return addT(pageIndex);
                } catch (WriterException ex) {
                    Logger.getLogger(TickerfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TickerfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
                
                
            }
        });
    anchor.setTopAnchor(Pag, 40.0);
        anchor.setRightAnchor(Pag, 10.0);
        anchor.setBottomAnchor(Pag, 10.0);
        anchor.setLeftAnchor(Pag, 10.0);
        anchor.getChildren().addAll(Pag);
       
        // TODO
    }    

    @FXML
    private void Print(ActionEvent event) throws MalformedURLException, IOException {
       
        WritableImage image = webview.snapshot(new SnapshotParameters(), null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );

        File file = fileChooser.showSaveDialog(webview.getScene().getWindow());
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                System.err.println("Failed to save image: " + e.getMessage());
            }
        }
    }

    @FXML
    private void ChangeToV(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml"));
        
        
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root, 300, 250);
        
        scene.getStylesheets().add(getClass().getResource("../resource/MyText.css").toExternalForm());
        Stage primaryStage=new Stage();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
   
 
    
}
