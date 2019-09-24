/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import function.reg;
import function.settable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class CustomerController implements Initializable {

     @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField route;

    @FXML
    private JFXTextField shopname;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXTextField addr;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton save;
    
    @FXML
    private TableView<?> tv;
    
    @FXML
    private TableColumn<?, ?> cusnm;

    @FXML
    private TableColumn<?, ?> comnm;
    
    @FXML
    private JFXButton edit;

    @FXML
    private JFXButton clear;
    
    @FXML
    private JFXButton delete;
    
    
     @FXML
    void clear(ActionEvent event) {
        name.setText("");
        route.setText("");
        shopname.setText("");
        tel.setText("");
        addr.setText("");
        email.setText("");
    }
    
    
    @FXML
    void edit(ActionEvent event) {
         if(tv.getSelectionModel().getSelectedItem() == null){
             JOptionPane.showMessageDialog(null, "Pls select a customer before editing");
         }else{
             TablePosition pos=tv.getSelectionModel().getSelectedCells().get(0);
             int row=pos.getRow();
             TableColumn col=pos.getTableColumn();
             String data=(String) col.getCellObservableValue(row).getValue();
             
             view v = new view();
             ResultSet rs= v.getTable("SELECT * FROM customer WHERE name='"+data+"' OR com_name='"+data+"'");
             
             String s = null;
             
             try {
                 while(rs.next()){
                     name.setText(rs.getString(1));
                     shopname.setText(rs.getString(2));
                     addr.setText(rs.getString(3));
                     tel.setText(rs.getString(4));
                     email.setText(rs.getString(5));
                     route.setText(rs.getString(7));
                     
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
    }

    
    @FXML
    void insert(ActionEvent event) {
        
        
        String nm = name.getText();
        String rout = route.getText();
        String comname=shopname.getText();
        String telep = tel.getText();
        String address= addr.getText();
        String mail = email.getText();
        
        reg in = new reg();
        in.getDetails(nm, comname, rout, telep, address, mail);
        in.setToTable();
        
        name.setText("");
        route.setText("");
        shopname.setText("");
        tel.setText("");
        addr.setText("");
        email.setText("");
        
       view rs = new view();
     ResultSet rst=rs.getTable("SELECT name,com_name FROM CUSTOMER");
     
     settable stb=new settable();
     stb.loadData(cusnm, comnm, rst, tv, "name", "com_name");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     view rs = new view();
     ResultSet rst=rs.getTable("SELECT name,com_name FROM CUSTOMER");
     
     settable stb=new settable();
     stb.loadData(cusnm, comnm, rst, tv, "name", "com_name");
    }    
    
    @FXML
    void delete(ActionEvent event) {
        
        if(tv.getSelectionModel().getSelectedItem() == null){
             JOptionPane.showMessageDialog(null, "Pls select a customer before deleting");
         }else{
             TablePosition pos=tv.getSelectionModel().getSelectedCells().get(0);
             int row=pos.getRow();
             TableColumn col=pos.getTableColumn();
             String data=(String) col.getCellObservableValue(row).getValue();
             
            view v = new view();
            v.insert("DELETE FROM customer WHERE name='"+data+"' OR com_name='"+data+"'");view rs = new view();
            ResultSet rst=rs.getTable("SELECT name,com_name FROM CUSTOMER");
     
            settable stb=new settable();
            stb.loadData(cusnm, comnm, rst, tv, "name", "com_name");
            JOptionPane.showMessageDialog(null, "Customer Deletion successfull");
    }
    
}
}
