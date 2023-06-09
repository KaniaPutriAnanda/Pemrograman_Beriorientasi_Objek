/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package pbologin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author ASUS
 */
public class PBOLogin extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            String resource = "LoginPage.fxml";
//            String resource = "/tableView/tableViewPage.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
            
            TextField txtUsername = (TextField) root.lookup("#txtUsername");
            txtUsername.requestFocus();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
