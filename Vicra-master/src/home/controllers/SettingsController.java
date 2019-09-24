/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import function.view;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class SettingsController implements Initializable {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField weight;

    @FXML
    private JFXTextField price;


    private Button search;

    
    void add(ActionEvent event) {
        view v = new view();
        String sql = "INSERT INTO products VALUES("+"'"+id.getText()+"',"+"'"+name.getText()+"',"+"'"+weight.getText()+"',"+"'"+price.getText()+"')";
        v.insert(sql);
        JOptionPane.showMessageDialog(null, "Product added successfully");
        
    }

    void delete(ActionEvent event) {
        view v = new view();
        String sql = "DELETE FROM products WHERE PID='"+id.getText()+"'";
        v.delete(sql,"products","PID",id.getText());
        
        id.setText("");
        name.setText("");
        weight.setText("");
        price.setText("");
        
    }

    void search(ActionEvent event) {

        view v=new view();
       ResultSet rst= v.search("products", "PID", search.getText());
        
        try {
            if(rst.getString(1)!=null){
        id.setText(rst.getString("PID"));
        name.setText(rst.getString("name"));
        weight.setText(rst.getString("weight"));
        price.setText(rst.getString("price"));
        
        }else{
            JOptionPane.showMessageDialog(null, "Pls Enter a valid Product ID!");
            }
        }catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    void update(ActionEvent event) {

    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
