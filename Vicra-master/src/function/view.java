/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;


import databse.Dbcon;
import home.controllers.StockController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javax.swing.JOptionPane;

/**
 *
 * @author ukavi
 */
public class view {
   String what;
   String tablenm;
   
   PreparedStatement ps;
    ResultSet rs;
    
    public ResultSet getTable(String wht){
       
        what=wht;
       
        try {
            ps=Dbcon.connection().prepareStatement(what);
            
            rs=ps.executeQuery();
        } catch (SQLException ex) { 
           Logger.getLogger(view.class.getName()).log(Level.SEVERE, null, ex);
       }
        
     return rs;   
    }
    
    public void loadchart(BarChart stock){
       XYChart.Series<String,Double> series=new XYChart.Series<>();
       
       ResultSet rst = this.getTable("SELECT products.name,products.weight,sum(products_raw.packets) as packets FROM products INNER JOIN products_raw ON products.PID=products_raw.PID GROUP BY products.PID");
       
    try {
        while(rst.next()){
            series.getData().add(new XYChart.Data<>(rst.getString(1)+" "+rst.getInt(2)+"g",rst.getDouble(3)));
            
        }
        
        stock.getData().add(series);
    } catch (SQLException ex) {
        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Error!");
    }
       
   }
    
    public void insert(String wh){
       try {
           ps=Dbcon.connection().prepareStatement(wh);
           ps.execute();
           JOptionPane.showMessageDialog(null, "Product added successfully");
           
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error on insert");
       }
        
    }
    
    public void in(String wh) throws SQLException{
       try {
           ps=Dbcon.connection().prepareStatement(wh);
           ps.execute();
           
           
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error");
       }finally{
           ps.close();
       }
        
    }
    public void delete(String wh,String tbl,String col,String val){
        what=wh;
       
       try {   
           ps=Dbcon.connection().prepareStatement(what);
           int c= ps.executeUpdate();
           
           System.out.println(c);
           if(c==0){
           JOptionPane.showMessageDialog(null, "Error on deletion1");
           }else{
               JOptionPane.showMessageDialog(null, "Product deleted successfully");
           }
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error on deletion");
       }
        
    }
    
    public ResultSet search(String tbl,String col,String val){
        String sql = "SELECT * FROM "+tbl+" WHERE "+col+"='"+val+"'";
        ResultSet rsta = null;
        int c = 0;
       try {
           ps=Dbcon.connection().prepareStatement(sql);
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "error2");
       }
            
//       try {
//           c = ps.executeUpdate();
//       } catch (SQLException ex) {
//           JOptionPane.showMessageDialog(null, "error1");
//       }
       try {
           rsta=ps.executeQuery();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "error");
       }
            
            
            
        
       
       return rsta;
    }
}
