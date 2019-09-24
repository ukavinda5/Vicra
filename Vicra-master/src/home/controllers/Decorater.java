/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;
import java.awt.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Decorater extends AnchorPane
{
    private double xOffset = 0;
    private double yOffset = 0;
    private Stage primaryStage;
    
    public Decorater(Stage stage, Node node) {
        super();
        
        primaryStage = stage;
        this.setPadding(new javafx.geometry.Insets(0, 0, 0, 0));
    
        this.getChildren().addAll(node);

        this.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
       
}

}

