/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

/**
 * FXML Controller class
 *
 * @author ukavi
 */
public class Ui3Controller implements Initializable {
   @FXML
    private BarChart<?, ?> stock;

    @FXML
    private CategoryAxis s;

    @FXML
    private NumberAxis y;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
}
