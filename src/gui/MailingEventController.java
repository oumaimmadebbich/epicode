/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;




import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javax.mail.*;  
import javax.mail.internet.*;  
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import entities.Event;
import services.EventInterface;
import services.EventService;

/**
 * FXML Controller class
 *
 * @author ZC-Lenovo
 */
public class MailingEventController implements Initializable {

    @FXML
    private TextField MailR;
    @FXML
    private TextArea MailM;
    @FXML
    private Button sendM;
     private int param;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
    }  
    

    @FXML
    private void SendMail(ActionEvent event) throws AddressException, MessagingException, IOException {
         EventInterface ps = new EventService();
         Event E=ps.getEV(param);
          String x=E.getImageEvent();
        String DBPath = "C:\\xampp\\htdocs\\Offre+Demande\\public\\uploads\\" + x;
        File file = new File(DBPath);
        Image image = new Image(file.toURI().toURL().toString());
     String to = MailR.getText();//change accordingly  
      String from = "the.amine.nouira@gmail.com";
    final String password="sshsgopprdtgbnpu";
      String host = "smtp.gmail.com";//or IP address  
  
     //Get the session object  
      Properties props = new Properties();  
   props.put("mail.smtp.host",host);
    props.put("mail.smtp.port", 465);
   props.put("mail.smtp.auth", "true");  
   props.put("mail.smtp.starttls.enable", "true");
           
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(from,password);  
      }  
    });  
     
  
    try {
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bImage, "png", baos);
    byte[] imageBytes = baos.toByteArray();
    DataSource source = new ByteArrayDataSource(imageBytes, "image/png");

    // create your HTML content with the image embedded
    String htmlContent = "<html><B><body><h1>Invitation to join our event with Reused.com" + E.getTitleEvent() + "</h1><img src=\"cid:image\"></body></html>";

    // create the message body parts
    MimeBodyPart imagePart = new MimeBodyPart();
    imagePart.setDataHandler(new DataHandler(source));
    imagePart.setHeader("Content-ID", "<image>");

    BodyPart htmlPart = new MimeBodyPart();
    htmlPart.setContent(htmlContent, "text/html");

    // create the multipart container to hold the message body parts
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(htmlPart);
    multipart.addBodyPart(imagePart);
   

    // set the multipart as the message content
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    message.setSubject("Invitation");
    message.setContent(multipart);
    
    // send the message
    Transport.send(message);
    System.out.println("Email sent successfully!");
} catch (Exception e) {
    System.out.println("Failed to send email: " + e.getMessage());
}
    }
     public void setParam(int param)  {
        this.param = param;}
    
}
