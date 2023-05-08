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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ZC-Lenovo
 */

public class QrCodeController implements Initializable {

    @FXML
    
    private ImageView QRView;
     private int param;
    @FXML
    private Button GQR;
    public void setParam(int param)  {
        this.param = param;
        
       
         
    }
    private  int param1;
  
    public void setParam1(int param)  {
        this.param1 = param;
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    
     private  void updateQrCodeData() throws WriterException, IOException {
            String qrCodeData = "http://127.0.0.1:8000/participation/bot/"+param1+"/"+param;
            String filePath = "codeqrr.png";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 300,300);
            
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(filePath));
            
           Image  image = new Image(new File(filePath).toURI().toString());
            QRView.setImage(image);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
       
           
        
            
           
           
            
        
            
        
        
    }   

    @FXML
    private void GenerateQr(ActionEvent event) throws IOException {
        try {
            this.updateQrCodeData();
        } catch (WriterException ex) {
            Logger.getLogger(QrCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
