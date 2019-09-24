/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class LoginController implements Initializable {
    
     @FXML
    private AnchorPane apane;

     @FXML
    private JFXButton loginbtn;

    @FXML
    private JFXTextField usertxt;

    @FXML
    private JFXPasswordField passtxt;
    
     @FXML
    void Exit(MouseEvent event) {
         System.exit(0);
    }
    @FXML
    void makelogin(ActionEvent event) {
       
        String username=usertxt.getText();
        String pass=passtxt.getText();
        System.out.println(usertxt); 
        System.out.println(passtxt); 
       
        if(username.equals("user")&&pass.equals("1234"))
        {
            Stage stage = (Stage) loginbtn.getScene().getWindow();
         stage.close();
         
            System.out.println("welcome");   
         loadStage("Home1.fxml");
         
         
         
         
         
        }else{
            Alert alert = new Alert(AlertType.ERROR,"Username or Paaword mismatch");
            alert.show();
            usertxt.setText("");
            passtxt.setText("");
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loginbtn.setDefaultButton(true);
    }    
   
    
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
           
           
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
}
