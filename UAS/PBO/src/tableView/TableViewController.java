/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableView;

import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
//import jdk.nashorn.internal.objects.annotations.Property;
import models.Amplang;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javafx.scene.control.TableRow;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import pbologin.LoginController;




/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TableViewController implements Initializable {
    
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    @FXML
    private TableView<Amplang> amplangTables;
    @FXML
    private TableColumn<Amplang, String> idCol;
    @FXML
    private TableColumn<Amplang, String> namaCol;
    @FXML
    private TableColumn<Amplang, String> hargaCol;
    @FXML
    private TableColumn<Amplang, String> varianCol;
    @FXML
    private TableColumn<Amplang, String> kategoriCol;
    @FXML
    private TableColumn<Amplang, String> stokCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Amplang amplang = null ;
    
    ObservableList<Amplang>  AmplangList = FXCollections.observableArrayList();

    @FXML
    private Button Search;
    
    @FXML
    private Button btnLihat;
    
     @FXML
    private Button btnLogout;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private TextField txtHarga;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtStok;
    
    @FXML
    private TextField txtSearch;
    
    @FXML
    private RadioButton radioButtonIkanTenggiri;

    @FXML
    private RadioButton radioButtonUdang;
    
    @FXML
    private CheckBox checkBoxAsin;

    @FXML
    private CheckBox checkBoxManis;

    @FXML
    private CheckBox checkBoxPedas;

    @FXML
    private Button btnBersih;
    
    @FXML
    void Add(ActionEvent event) {
        String nama, varian = null, kategori = "";
        int harga, stok;

        nama = txtNama.getText();
        harga = Integer.parseInt(txtHarga.getText());
        stok = Integer.parseInt(txtStok.getText());

        ToggleGroup varianGroup = new ToggleGroup();
        radioButtonUdang.setToggleGroup(varianGroup);
        radioButtonIkanTenggiri.setToggleGroup(varianGroup);

        // Mendapatkan nilai dari RadioButton varian
        RadioButton selectedRadioButton = (RadioButton) varianGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            varian = selectedRadioButton.getText();
        }

        // Mendapatkan nilai dari CheckBox kategori
        if (checkBoxManis.isSelected()) {
            kategori += "Manis ";
        }
        if (checkBoxAsin.isSelected()) {
            kategori += "Asin ";
        }
        if (checkBoxPedas.isSelected()) {
            kategori += "Pedas";
        }

        try {
            
            con = DbConnect.getConnect();
            
            pst = con.prepareStatement("INSERT INTO tbamplang (nama, harga, varian, kategori, stok) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, nama);
            pst.setInt(2, harga);
            pst.setString(3, varian);
            pst.setString(4, kategori);
            pst.setInt(5, stok);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AmplangFever");
            alert.setHeaderText("AmplangFever");
            alert.setContentText("Berhasil Ditambahkan!");

            alert.showAndWait();

            refreshTable();

            txtNama.setText("");
            txtHarga.setText("");
            txtStok.setText("");
            radioButtonUdang.setSelected(false);
            radioButtonIkanTenggiri.setSelected(false);
            checkBoxManis.setSelected(false);
            checkBoxAsin.setSelected(false);
            checkBoxPedas.setSelected(false);
            txtNama.requestFocus();
            
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
void Delete(ActionEvent event) {
    myIndex = amplangTables.getSelectionModel().getSelectedIndex();
    id = Integer.parseInt(String.valueOf(amplangTables.getItems().get(myIndex).getId()));

    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Konfirmasi");
    confirmationAlert.setHeaderText("Konfirmasi Hapus");
    confirmationAlert.setContentText("Apakah Anda yakin ingin menghapus item ini?");

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            con = DbConnect.getConnect();
            
            pst = con.prepareStatement("DELETE FROM tbamplang WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("AmplangFever");
            successAlert.setHeaderText("AmplangFever");
            successAlert.setContentText("Berhasil Dihapus!");

            successAlert.showAndWait();
            refreshTable();

            txtNama.setText("");
            txtHarga.setText("");
            txtStok.setText("");
            radioButtonUdang.setSelected(false);
            radioButtonIkanTenggiri.setSelected(false);
            checkBoxManis.setSelected(false);
            checkBoxAsin.setSelected(false);
            checkBoxPedas.setSelected(false);
            txtNama.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    @FXML
    void Update(ActionEvent event) {
        
        String nama, varian = null, kategori = "";
        int harga, stok;
        
        myIndex = amplangTables.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(amplangTables.getItems().get(myIndex).getId()));
          
        nama = txtNama.getText();
        harga = Integer.parseInt(txtHarga.getText());
        stok = Integer.parseInt(txtStok.getText());

        // Mendapatkan nilai dari RadioButton varian
        if (radioButtonUdang.isSelected()) {
            varian = "Udang";
        } else if (radioButtonIkanTenggiri.isSelected()) {
            varian = "Ikan Tenggiri";
        }

        // Mendapatkan nilai dari CheckBox kategori
        if (checkBoxManis.isSelected()) {
            kategori += "Manis ";
        }
        if (checkBoxAsin.isSelected()) {
            kategori += "Asin ";
        }
        if (checkBoxPedas.isSelected()) {
            kategori += "Pedas";
        }
        
        try
        {
            con = DbConnect.getConnect();
            
            pst = con.prepareStatement("update tbamplang set nama = ?,harga = ?,varian = ?,kategori = ?, stok = ? where id = ? ");
            pst.setString(1, nama);
            pst.setInt(2, harga);
            pst.setString(3, varian);
            pst.setString(4, kategori);
            pst.setInt(5, stok);
            pst.setInt(6, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AmplangFever");

            alert.setHeaderText("AmplangFever");
            alert.setContentText("Berhasil Diperbarui!");

            alert.showAndWait();
            refreshTable();
            
            txtNama.setText("");
            txtHarga.setText("");
            txtStok.setText("");
            radioButtonUdang.setSelected(false);
            radioButtonIkanTenggiri.setSelected(false);
            checkBoxManis.setSelected(false);
            checkBoxAsin.setSelected(false);
            checkBoxPedas.setSelected(false);
            txtNama.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void search(ActionEvent event) {
        String keyword = txtSearch.getText();
    
        // Buatlah query sesuai dengan kondisi pencarian yang Anda inginkan
        String query = "SELECT * FROM tbamplang WHERE nama LIKE '%" + keyword + "%'";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            AmplangList.clear(); // Hapus data yang ada sebelumnya

            while (resultSet.next()) {
                AmplangList.add(new Amplang(
                        resultSet.getInt("id"),
                        resultSet.getString("nama"),
                        resultSet.getInt("harga"),
                        resultSet.getString("varian"),
                        resultSet.getString("kategori"),
                        resultSet.getInt("stok")));
            }

            amplangTables.setItems(AmplangList); // Perbarui tampilan tabel dengan hasil pencarian
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Konfirmasi");
    alert.setHeaderText("Konfirmasi Logout");
    alert.setContentText("Apakah Anda yakin ingin keluar?");
    
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbologin/LoginPage.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login");
        Scene scene = new Scene(root, 600, 400);

        TextField txtUsername = (TextField) root.lookup("#txtUsername");
        txtUsername.requestFocus();
        btnLogout.getScene().getWindow().hide();
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
    }
}


    @FXML
    void Clear(ActionEvent event) {
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        radioButtonUdang.setSelected(false);
        radioButtonIkanTenggiri.setSelected(false);
        checkBoxManis.setSelected(false);
        checkBoxAsin.setSelected(false);
        checkBoxPedas.setSelected(false);
        txtNama.requestFocus();
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

      
    
    @FXML
private void refreshTable() {
    try {
        AmplangList.clear();
        
        query = "SELECT * FROM tbamplang";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            AmplangList.add(new models.Amplang(
                    resultSet.getInt("id"),
                    resultSet.getString("nama"),
                    resultSet.getInt("harga"),
                    resultSet.getString("varian"),
                    resultSet.getString("kategori"),
                    resultSet.getInt("stok")));
        }
        
        amplangTables.setItems(AmplangList);
        
    } catch (SQLException ex) {
        Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
    
    amplangTables.setRowFactory(tv -> {
        TableRow<models.Amplang> myRow = new TableRow<>();
        myRow.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                myIndex = amplangTables.getSelectionModel().getSelectedIndex();
                id = amplangTables.getItems().get(myIndex).getId();
                txtNama.setText(amplangTables.getItems().get(myIndex).getNama());
                txtHarga.setText(String.valueOf(amplangTables.getItems().get(myIndex).getHarga()));
                
                models.Amplang selectedAmplang = amplangTables.getItems().get(myIndex);

                if (selectedAmplang.getVarian().equals("Udang")) {
                    radioButtonUdang.setSelected(true);
                } else if (selectedAmplang.getVarian().equals("Ikan Tenggiri")) {
                    radioButtonIkanTenggiri.setSelected(true);
                }

                checkBoxManis.setSelected(false);
                checkBoxAsin.setSelected(false);
                checkBoxPedas.setSelected(false);

                String kategori = selectedAmplang.getKategori();
                if (kategori.contains("Manis")) {
                    checkBoxManis.setSelected(true);
                }
                if (kategori.contains("Asin")) {
                    checkBoxAsin.setSelected(true);
                }
                if (kategori.contains("Pedas")) {
                    checkBoxPedas.setSelected(true);
                }
                
                txtStok.setText(String.valueOf(amplangTables.getItems().get(myIndex).getStok()));
            }
        });
        return myRow;
    });
}


    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));
        varianCol.setCellValueFactory(new PropertyValueFactory<>("varian"));
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        stokCol.setCellValueFactory(new PropertyValueFactory<>("stok"));
    }
    
}