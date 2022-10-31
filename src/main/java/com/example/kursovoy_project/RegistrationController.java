package com.example.kursovoy_project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML Label errorText;

    @FXML
    private TextField regSurname;
    Connection connection;

    @FXML
    public void Registration() throws Exception{
        String name = regName.getText();
        String surname = regSurname.getText();
        String login = regLogin.getText();
        String password = regPassword.getText();
        if(name.isEmpty() | login.isEmpty() | login.isEmpty() | password.isEmpty()){
            errorText.setText("Ошибка регистрации.");
        }
        else{
            boolean isRegistered = false;

            connection = Connector.ConnectDb();
            ResultSet rs = connection.createStatement().executeQuery("select Логин from `group`.accounts");
            while (rs.next()){
                if(login.equals(rs.getString("Логин"))){
                    isRegistered = true;
                    JOptionPane.showMessageDialog(null, "Пользователь с данным логином уже существует");
                    break;
                }
            }
            if (!isRegistered){
                PreparedStatement st = connection.prepareStatement("insert into accounts " +
                        "(Логин, Пароль, Имя, Фамилия) value (?, ?, ?, ?)");

                st.setString(1, login);
                st.setString(2, password);
                st.setString(3, name);
                st.setString(4, surname);

                st.execute();
                JOptionPane.showMessageDialog(null, "Регистрация успешно завершена! Вернитесь на главную страницу и авторизуйтесь");
            }
        }
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
            stage.setTitle("Role");
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
