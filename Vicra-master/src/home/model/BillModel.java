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
public class BillModel {
    String product,weight;
    int quantity;
    double Price,Total;

    public BillModel(String product, String weight, int quantity, double Price) {
        this.product = product;
        this.weight = weight;
        this.quantity = quantity;
        this.Price = Price;
        this.Total = Price*quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPri(double pri) {
        this.Price = pri;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
}
