/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pbologin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ASUS
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPass;
    
    String uname = "kananda";
    String pass = "123";
    Alert alert;
            
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (txtUsername.getText().equals(uname) && txtPass.getText().equals(pass)){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
            Parent root = loader.load();
            stage.setTitle("Login");
            Scene scene = new Scene(root, 600, 400);
            
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Masuk");
            alert.showAndWait();

            btnLogin.getScene().getWindow().hide();
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } 
        else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);         
            alert.setContentText("Username/PasswordSalah");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
        @Override
        public void run() {
            txtUsername.requestFocus();
        }
    });
    }    
    
}
