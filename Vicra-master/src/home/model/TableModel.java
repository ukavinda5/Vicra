/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

/**
 *
 * @author ukavi
 */
public class TableModel {
    
    String name,quantity,up,weight;
    
    public TableModel(String name, String quantity, String up) {
        this.name = name;
        this.quantity = quantity;
        this.up = up;
    }
    
    public TableModel(String name, String quantity, String up,String weight) {
        this.name = name;
        this.quantity = quantity;
        this.up = up;
        this.weight=weight;
    }

    public TableModel(String nm, String com) {
        name=nm;
        quantity=com;
    }
    
    public String getName() {
        return name;
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    
    
}
