/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import databse.Dbcon;
import function.view;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class ProductionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        view v = new view();
        ResultSet rs = v.getTable("SELECT name,weight FROM products");
        ResultSet rst = v.getTable("SELECT name FROM rawmaterial");

        try {
            while (rs.next()) {
                procomb1.getItems().addAll(rs.getString("name") + " " + rs.getString("weight") + "g");
                procomb2.getItems().addAll(rs.getString("name") + " " + rs.getString("weight") + "g");
            }
            while (rst.next()) {
                matcomb1.getItems().addAll(rst.getString(1));
                matcomb2.getItems().addAll(rst.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                rst.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private JFXComboBox<String> procomb1;

    @FXML
    private JFXButton in2;

    @FXML
    private JFXTextField qty3;

    @FXML
    private JFXComboBox<String> procomb2;

    @FXML
    private JFXButton out2;

    @FXML
    private JFXTextField qty4;

    @FXML
    private JFXComboBox<String> matcomb2;

    @FXML
    private JFXButton out1;

    @FXML
    private JFXTextField qty2;

    @FXML
    private JFXTextField qty1;

    @FXML
    private JFXComboBox<String> matcomb1;

    @FXML
    private JFXButton in1;

    @FXML
    private DatePicker prodate;

    @FXML
    private DatePicker matdate;

    @FXML
    void matin(ActionEvent event) throws SQLException{

        PreparedStatement ps = null;
        try {
            String item = matcomb1.getSelectionModel().getSelectedItem();
            Double matqty = Double.valueOf(qty1.getText());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            ps = Dbcon.connection().prepareStatement("INSERT INTO rawmaterial_inventory(quantity,date,name) values(?,?,?)");
            ps.setDouble(1, matqty);
            ps.setString(2, date);
            ps.setString(3, item);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Success");
            qty1.setText("");
            matcomb1.getSelectionModel().select(-1);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Failed");
        }finally{
            ps.close();
        }

    }

    @FXML
    void matout(ActionEvent event){

        PreparedStatement pst = null;
       
            String item = matcomb2.getSelectionModel().getSelectedItem();
            String date = matdate.getValue().format(DateTimeFormatter.ofPattern(""));
            
            String sql = "DELETE from rawmaterial_inventory WHERE name=? AND date=?";
         try {
            pst = Dbcon.connection().prepareStatement(sql);
            pst.setString(1, item);
            pst.setString(2, date);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Success");
            qty2.setText("");
            matcomb2.getSelectionModel().select(-1);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Failed");
        }finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    @FXML
    void proin(ActionEvent event) {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        String id=null;
        
        String item = procomb1.getSelectionModel().getSelectedItem();
        String[] name = item.split("\\s+");
        try {
            ps1 = Dbcon.connection().prepareStatement("SELECT PID FROM products WHERE name=? AND weight=?");

            if (name.length > 2) {
                ps1.setString(1, name[0] + " " + name[1]);
            } else {
                ps1.setString(1, name[0]);
            }
            ps1.setString(2, name[name.length - 1].substring(0, name[name.length - 1].length() - 1));
            rs = ps1.executeQuery();
            
            while (rs.next()) {
                id = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {
                rs.close();
                ps1.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        
        int proqty = Integer.valueOf(qty3.getText());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ResultSet rst=null;
        
        try {
                ps = Dbcon.connection().prepareStatement("SELECT rawmaterial.totalstock,products.rawname FROM rawmaterial JOIN products ON rawmaterial.name=products.rawname WHERE products.PID='"+id+"'");
                rst=ps.executeQuery();
                double stock = 0;
                String rawname=null;
                while(rst.next()){
                    stock=rst.getDouble("totalstock");
                    rawname=rst.getString("rawname");
                }
          int weight= Integer.parseInt(name[name.length - 1].substring(0, name[name.length - 1].length() - 1));
          int total=(weight * proqty)/1000;
          double newt=stock-total;
          if(stock<total){
              JOptionPane.showMessageDialog(null, "False data insertion!");
          }else{
          ps = Dbcon.connection().prepareStatement("UPDATE rawmaterial SET totalstock="+newt+" WHERE name='"+rawname+"'");
          ps.execute();
          
          try {
            ps = Dbcon.connection().prepareStatement("INSERT INTO products_raw(PID,DOM,packets) values(?,?,?)");
            ps.setString(1, id);
            ps.setString(2, date);
            ps.setInt(3, proqty);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Success");
            qty3.setText("");
            procomb1.getSelectionModel().select(-1);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Failed");
        }
    }
        }catch (SQLException ex) {
                Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          
        }

    

    @FXML
    void proout(ActionEvent event){

        PreparedStatement pst = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String id=null;
        

            String item = procomb2.getSelectionModel().getSelectedItem();
            String[] name = item.split("\\s+");
        try {
            ps = Dbcon.connection().prepareStatement("SELECT PID FROM products WHERE name=? AND weight=?");
            
            if (name.length > 2) {
                ps.setString(1, name[0] + " " + name[1]);
            } else {
                ps.setString(1, name[0]);
            }
            ps.setString(2, name[name.length - 1].substring(0, name[name.length - 1].length() - 1));
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
            
            String date = prodate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String sql = "DELETE from products_raw WHERE PID=? AND DOM=?";

        try {
            pst = Dbcon.connection().prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, date);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Success");
            qty4.setText("");
            procomb2.getSelectionModel().select(-1);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Failed");
        }finally{
                 
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
       
            
            
        
       
          

        

    }

}
