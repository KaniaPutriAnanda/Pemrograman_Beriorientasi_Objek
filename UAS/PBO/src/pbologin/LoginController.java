/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pbologin;

import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;
    
    
//    String uname = "kananda";
//    String pass = "123";
//    Alert alert;
            
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        try {
            
            con = DbConnect.getConnect();
            
            pst = con.prepareStatement("SELECT * FROM akun WHERE username = ? AND password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tableView/tableViewPage.fxml"));
                Parent root = loader.load();
                stage.setTitle("AmplangFever");
                Scene scene = new Scene(root, 679, 587);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Berhasil");
                alert.setHeaderText(null);
                alert.setContentText("Berhasil Masuk");
                alert.showAndWait();
                
                btnLogin.getScene().getWindow().hide();
                stage.initStyle(StageStyle.UTILITY);
                stage.setScene(scene);
                stage.show();
                // Login berhasil
                // Lakukan tindakan yang sesuai, misalnya navigasi ke halaman utama
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login Gagal");
                alert.setHeaderText(null);         
                alert.setContentText("Username/Password Salah");
                alert.showAndWait();
                txtPassword.setText("");
                // Login gagal
                // Tampilkan pesan kesalahan atau lakukan tindakan lainnya
            }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
        
        
        
//        if (txtUsername.getText().equals(uname) && txtPass.getText().equals(pass)){
//            Stage stage = new Stage();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
//            Parent root = loader.load();
//            stage.setTitle("Login");
//            Scene scene = new Scene(root, 600, 400);
//            
//            alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Login Berhasil");
//            alert.setHeaderText(null);
//            alert.setContentText("Berhasil Masuk");
//            alert.showAndWait();
//
//            btnLogin.getScene().getWindow().hide();
//            stage.initStyle(StageStyle.DECORATED);
//            stage.setScene(scene);
//            stage.show();
//        } 
//        else {
//            alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Login Gagal");
//            alert.setHeaderText(null);         
//            alert.setContentText("Username/PasswordSalah");
//            alert.showAndWait();
//        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection connection = DbConnect.getConnect();
//        Platform.runLater(new Runnable() {
//        @Override
//        public void run() {
//            txtUsername.requestFocus();
//        }
//    });
    }    
    
}
