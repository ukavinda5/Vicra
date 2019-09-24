package home.controllers;

import function.settable;
import home.model.TableModel;
import function.stageload;
import function.view;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TableColumn;

public class StockController implements Initializable {
stageload st = new stageload();
    
     @FXML
    private BarChart<String, Double> stock;

    @FXML
    private CategoryAxis s;

    @FXML
    private NumberAxis y;

    @FXML
    private TableView<TableModel> tview;
     @FXML
    private TableColumn<TableModel, String> nm;

    @FXML
    private TableColumn<TableModel, String> up;

    @FXML
    private TableColumn<TableModel, String> qty;
       
     @FXML
    private PieChart pie;
     
     @FXML
    private TableView<?> tview1;

    @FXML
    private TableColumn<?, ?> nm1;

    @FXML
    private TableColumn<?, ?> wg1;
     
    @FXML
    private TableColumn<?, ?> up1;

    @FXML
    private TableColumn<?, ?> qty1;

     
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       view rs = new view();
       settable stable = new settable();
       ResultSet a = rs.getTable("SELECT rawmaterial.name,rawmaterial.totalstock ,sum(rawmaterial_inventory.quantity) as quantity FROM rawmaterial INNER JOIN rawmaterial_inventory ON rawmaterial.name=rawmaterial_inventory.name GROUP BY rawmaterial_inventory.name");
       stable.loadData(nm,qty,up,a,tview,"name","quantity","totalstock");
       
       ResultSet b = rs.getTable("SELECT products.name,products.price,products.weight,sum(products_raw.packets) as packets FROM products INNER JOIN products_raw ON products.PID=products_raw.PID GROUP BY products.PID");
       stable.loadData(nm1,wg1, qty1, up1,b,tview1,"name","weight","packets","price");
       
       rs.loadchart(stock);
       
       
//       PieChart pie=new PieChart();
//       ObservableList<PieChart.Data> data =
//         FXCollections.observableArrayList();
//      data.addAll(new PieChart.Data("Asia", 30.0),
//         new PieChart.Data("Africa", 20.3),
//         new PieChart.Data("North America", 16.3),
//         new PieChart.Data("South America", 12.0),
//         new PieChart.Data("Antartica", 8.9),
//         new PieChart.Data("Europe", 6.7),
//         new PieChart.Data("Australia", 5.2));
//
//      pie.setData(data);
//      pie.setTitle("The Continents: Land Area");
      
    }

   
    
    
    }



