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
import databse.Dbcon;
import function.settable;
import function.view;
import home.model.BillModel;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class BillingController implements Initializable {

     @FXML
    private TableView<BillModel> tb;
    @FXML
    private TableColumn<BillModel, String> proc;
    @FXML
    private TableColumn<BillModel, String> weic;
    @FXML
    private TableColumn<BillModel, String> qtyc;
    @FXML
    private TableColumn<BillModel, String> price;
    @FXML
    private TableColumn<BillModel, String> total;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> cus;
    @FXML
    private JFXComboBox<String> rout;
    @FXML
    private JFXComboBox<String> pro;
    @FXML
    private JFXComboBox<String> wei;
    @FXML
    private JFXTextField qty;
    @FXML
    private JFXButton in;

    @FXML
    private TextField discount;
    
     @FXML
    private JFXButton bill;
     
    @FXML
    private JFXButton del;

    @FXML
    private Label grandt;
    
    @FXML
    private Label totalprice;
    @FXML
    private JFXButton clr;

    @FXML
    private JFXButton billit;
    
    
    @FXML
    void tobill(ActionEvent event) throws SQLException, JRException {

        String customer=cus.getSelectionModel().getSelectedItem();
        view v=new view();
        
        String sql1="DELETE FROM bill";
        v.in(sql1);
        
          
        PreparedStatement ps1;
        String sql="INSERT INTO bill values(?,?,?,?,?)";
        ps1=Dbcon.connection().prepareStatement(sql);
        ObservableList<BillModel> data=tb.getItems();
       try{
           for(int i=0;i<data.size();i++){
            ps1.setString(1, data.get(i).getProduct());
            ps1.setString(2, data.get(i).getWeight());
            ps1.setString(3, String.valueOf(data.get(i).getQuantity()));
            ps1.setString(4, String.valueOf(data.get(i).getPrice()));
            ps1.setString(5, String.valueOf(data.get(i).getTotal()));
            
            ps1.execute();
        }}finally{
              ps1.close();
                }
       
        try{
            JRBeanCollectionDataSource bcs=new JRBeanCollectionDataSource(data);
            Map par=new HashMap();
        JasperReport jasperReport= JasperCompileManager.compileReport("C:\\Users\\ukavi\\OneDrive\\Pictures\\Vicra-master\\Vicra-master\\build\\classes\\home\\controllers\\report1.jrxml");
       
       
       JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, par ,bcs);
       
       JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\ukavi\\OneDrive\\Pictures\\Vicra-master\\Vicra-master\\build\\classes\\home\\controllers\\report1.pdf");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error!");
        
    }finally{
           try{
           v.in("UPDATE customer set total_bill='"+grandt.getText()+"' WHERE com_name='"+customer+"'");
           
           String st="UPDATE products set totalsales=? where name=? AND weight=?";
           ps1=Dbcon.connection().prepareStatement(st); 
           for(int i=0;i<data.size();i++){
               ps1.setInt(1, data.get(i).getQuantity());
               ps1.setString(2, data.get(i).getProduct());
               ps1.setString(3, data.get(i).getWeight());
               ps1.execute();
           }
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Error!!");
           }
        }
    }
    
    @FXML
    void clear(ActionEvent event) {

        tb.getItems().clear();
        totalprice.setText("");
        discount.setText("");
        grandt.setText("");
        
    }

    @FXML
    void delete(ActionEvent event) {

        BillModel selectedItem = tb.getSelectionModel().getSelectedItem();
        double tprice=tb.getSelectionModel().getSelectedItem().getTotal();
        tb.getItems().remove(selectedItem);
        double oldprice=Double.valueOf(totalprice.getText());
        double newprice=oldprice-tprice;
        totalprice.setText(String.valueOf(newprice));
        
        int dis=Integer.parseInt(discount.getText());
        double ttal= Double.valueOf(totalprice.getText());
        double gttal=ttal-((ttal*dis)/100);
        grandt.setText(String.valueOf(gttal));
        
    }
    
    /**
     * Initializes the controller class.
     */
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        view v=new view();
        ResultSet rs=v.getTable("SELECT DISTINCT(name) FROM products");
        ResultSet rs1=v.getTable("SELECT DISTINCT(weight) FROM products");
        ResultSet rst=v.getTable("SELECT com_name,route FROM customer");
        try {
            while(rs.next()){
                pro.getItems().addAll(rs.getString("name"));
            }
            while(rs1.next()){
                wei.getItems().addAll(rs1.getString("weight"));
            }
             while(rst.next()){
                 cus.getItems().addAll(rst.getString("com_name"));
                 rout.getItems().addAll(rst.getString("route"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                rs1.close();
                rst.close();
            } catch (SQLException ex) {
                Logger.getLogger(BillingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }    

    
    
    
   
    @FXML
    private void totable(ActionEvent event) throws SQLException {
        
         int i=3;
//    String customer = cus.getSelectionModel().getSelectedItem();
    if(cus.getSelectionModel().isEmpty()){
        JOptionPane.showMessageDialog(null, "Pls select a customer!");
    }
//    String route = rout.getSelectionModel().getSelectedItem();
     if(rout.getSelectionModel().isEmpty()){
        JOptionPane.showMessageDialog(null, "Pls select a route!");
    }
    String product = pro.getSelectionModel().getSelectedItem();
     if(pro.getSelectionModel().isEmpty()){
        JOptionPane.showMessageDialog(null, "Pls select a product!");
       i--; 
    }
    String weight = wei.getSelectionModel().getSelectedItem();
    if(wei.getSelectionModel().isEmpty()){
        JOptionPane.showMessageDialog(null, "Pls select the weight!");
        i--;
    }
    int quantity = Integer.parseInt(qty.getText());
    if(quantity==0){
        JOptionPane.showMessageDialog(null, "Pls enter the quantity!");
        i--;
    }else if(Pattern.matches("[a-zA-Z]+", qty.getText()) == true){
        JOptionPane.showMessageDialog(null, "Quantity must be numeric!");
        i--;
        qty.setText("");
    }
        
    ResultSet rs;
     double pri = 0;
    
    if(i==3){
        view v=new view();
      
        rs=v.getTable("SELECT price FROM products WHERE name='"+product+"' AND weight='"+weight+"'");
        while(rs.next()){
            pri=rs.getDouble(1);
        }
       
       settable st=new settable();
       st.appendData(proc, weic, qtyc, price, total, tb, product, weight,quantity,pri);
       
        double ttal = 0.0;
        for (BillModel item : tb.getItems()) {
        ttal = ttal + item.getTotal();
        }
         
        totalprice.setText(String.valueOf(ttal));
       
        pro.setValue(null);
        wei.setValue(null);
        qty.setText("");
    } else{
        JOptionPane.showMessageDialog(null, "Item selection wrong!");
        pro.setValue(null);
        wei.setValue(null);
        qty.setText("");
    }  
    
    }
    
    
    @FXML
    void topdf(ActionEvent event) {

        
    }
    
     @FXML
    void onEnter(ActionEvent event) {

        int dis=Integer.parseInt(discount.getText());
        double ttal= Double.valueOf(totalprice.getText());
        double gttal=ttal-((ttal*dis)/100);
        grandt.setText(String.valueOf(gttal));
    }
}
