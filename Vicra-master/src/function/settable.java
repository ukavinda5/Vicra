/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import home.controllers.StockController;
import home.model.BillModel;
import home.model.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ukavi
 */
public class settable {
    
    public void loadData(TableColumn one,TableColumn two,ResultSet data,TableView tv,String c1,String c2){
        ObservableList<TableModel> list = FXCollections.observableArrayList();
        
    try {
        while(data.next()){
            list.add(new TableModel(data.getString(c1),data.getString(c2)));
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
    }
        one.setCellValueFactory(new PropertyValueFactory<>("name"));
        two.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
    
        tv.setItems(list);
    }
    
    
     
    public void loadData(TableColumn one,TableColumn two,TableColumn three,ResultSet data,TableView tv,String c1,String c2,String c3){
        ObservableList<TableModel> list = FXCollections.observableArrayList();
        
    try {
        while(data.next()){
            list.add(new TableModel(data.getString(c1),data.getString(c2), data.getString(c3)));
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
    }
        one.setCellValueFactory(new PropertyValueFactory<>("name"));
        two.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        three.setCellValueFactory(new PropertyValueFactory<>("up"));
    
        tv.setItems(list);
    }
     
    
    public void loadData(TableColumn one,TableColumn two,TableColumn three,TableColumn four,ResultSet data,TableView tv,String c1,String c2,String c3,String c4){
        ObservableList<TableModel> list = FXCollections.observableArrayList();
        
    try {
        while(data.next()){
            list.add(new TableModel(data.getString(c1),data.getString(c2), data.getString(c3),data.getString(c4)));
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
    }
        one.setCellValueFactory(new PropertyValueFactory<>("name"));
        four.setCellValueFactory(new PropertyValueFactory<>("weight"));
        two.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        three.setCellValueFactory(new PropertyValueFactory<>("up"));
        
        tv.setItems(list);
    }
    
    
    public void appendData(TableColumn one,TableColumn two,TableColumn three,TableColumn four,TableColumn five,TableView tv,String c1,String c2,int c3,double c4){
        ObservableList<BillModel> list = FXCollections.observableArrayList();
        
   
            list.add(new BillModel(c1,c2,c3,c4));
            
        
        one.setCellValueFactory(new PropertyValueFactory<>("product"));
        two.setCellValueFactory(new PropertyValueFactory<>("weight"));
        three.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        four.setCellValueFactory(new PropertyValueFactory<>("Price"));
        five.setCellValueFactory(new PropertyValueFactory<>("Total"));
        
        tv.getItems().addAll(list);
    }
    
   
    
    
}
