package com.example.kursovoy_project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.*;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBackRole;

    @FXML
    private Button regButton;

    @FXML
    private TextField regLogin;

    @FXML
    private TextField regName;

    @FXML
    private TextField regPassword;

    @FXML
    private TextField regSurname;
    private JOptionPane JOptionPane;

    @FXML
    public void Registration() {
        JOptionPane.showMessageDialog(null, "Регистрация успешно завершена! Вернитесь на главную страницу и авторизуйтесь!");
    }
    public void backButton(){
        try {
            Stage stageToClose = (Stage) btnBackRole.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("role.fxml").openStream());
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
