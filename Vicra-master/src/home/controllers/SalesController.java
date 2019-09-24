/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class SalesController implements Initializable {

    @FXML
    private PieChart Ach;

    /**
     * Initializes the controller class.
     */
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
    
}
