/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import databse.Dbcon;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ukavi
 */
public class reg {
    String name;
    String com_name;
    String route;
    String telephone;
    String address;
    String email;
    String date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
    
    public void getDetails(String name, String comname, String Route, String tel, String addr,String email){
        this.name=name;
        com_name=comname;
        route=Route;
        telephone=tel;
        address=addr;
        this.email=email;
        
    }
    
    public void setToTable(){
        PreparedStatement ps=null;
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
        
        try {
            ps=Dbcon.connection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, com_name);
            ps.setString(3, address);
            ps.setString(4, telephone);
            ps.setString(5, email);
            ps.setString(6, date);
            ps.setString(7, route);
            ps.execute();
        } catch (SQLException e) {
            
        }
        
    
    
    }
    
}
