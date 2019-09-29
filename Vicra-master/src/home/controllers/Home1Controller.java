/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class Home1Controller implements Initializable {

   
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
        
  
    
    @FXML
     BorderPane boderpane;
    
    
  
    

    @FXML
    void stock(MouseEvent event) {
        loadUi("Stock");
    }
 
    @FXML
    void Customer(MouseEvent event) {
        loadUi("Customer");
    }

    @FXML
    void Billing(MouseEvent event) {
        loadUi("Billing");

    }

    @FXML
    void forcast(MouseEvent event) {
        loadUi("Forcast");
    }

    @FXML
    void production(MouseEvent event) {
        loadUi("Production");
    }

    @FXML
    void sales(MouseEvent event) {
        loadUi("Sales");
    }

    @FXML
    void settings(MouseEvent event) {
         loadUi("settings");
    }
   

    @FXML
    private TableColumn<?, ?> proc;

    @FXML
    private TableColumn<?, ?> weic;

    @FXML
    private TableColumn<?, ?> qtyc;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> total;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXComboBox<?> cus;

    @FXML
    private JFXComboBox<?> rout;

    @FXML
    private JFXComboBox<String> pro;

    @FXML
    private JFXComboBox<?> wei;

    @FXML
    private JFXTextField qty;

    @FXML
    private JFXButton in;
    
     @FXML
    private JFXButton EXIT;

    

    @FXML
    void Exit(MouseEvent event) {
        System.exit(0);
    }
    @FXML
    void totable(ActionEvent event) {

    }
     @FXML
    private PieChart Ach;
  @Override
    public void initialize(URL url, ResourceBundle rb) {
          loadAch(Ach);
    
    }

     private void loadAch(PieChart pieChart) {
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                        new PieChart.Data("Hash",25),
                        new PieChart.Data("Key",75)
                );
             pieChart.setData(data);
     }
    
    private void loadUi(String ui) {
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(Home1Controller.class.getName()).log(Level.SEVERE,null,ex);
        }
        boderpane.setCenter(root);
        
        //Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Home1.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Home1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(new Decorater(stage,root));
        scene.setFill(null);
        
        
        stage.setScene(scene);
        stage.show();
    }
    
   
    
      
   

}


