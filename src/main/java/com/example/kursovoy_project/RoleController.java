package com.example.kursovoy_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RoleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnExecutor;

    @FXML
    private Button btnRegistration;

    @FXML
    public void registrationUser() {
        try {
            Stage stageToClose  = (Stage) btnRegistration.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("registration-window.fxml").openStream());
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
            stage.setTitle("Authorization");
            stage.show();
            stage.setResizable(false);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void roleSelection(){
        try {
            Stage stageToCloseAdmin = (Stage) btnAdmin.getScene().getWindow();
            stageToCloseAdmin.close();

            Stage stageToCloseExecutor = (Stage) btnExecutor.getScene().getWindow();
            stageToCloseExecutor.close();

            Stage stageToCloseClient = (Stage) btnClient.getScene().getWindow();
            stageToCloseClient.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("authorization-window.fxml").openStream());
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
            stage.setTitle("Authorization");
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
