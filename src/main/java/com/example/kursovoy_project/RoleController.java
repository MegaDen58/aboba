package com.example.kursovoy_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class RoleController {

    public static String status;
    @FXML
    private TextField authLogin;

    @FXML
    private PasswordField authPassword;
    @FXML
    private Button btnAdmin;
    @FXML
    private Button btnClient;

    @FXML
    private Button btnExecutor;

    @FXML
    private Button btnRegistration;
    @FXML Button authButton;
    @FXML Button backButton;
    @FXML Label errorText;
    Connection connection;
    ResultSet resultSet;
    Stage stage = new Stage();
    FXMLLoader fxmlLoader = new FXMLLoader();


    @FXML
    public void registrationUser() {
        try {
            Stage stageToClose  = (Stage) btnRegistration.getScene().getWindow();
            stageToClose.close();

            Pane root = fxmlLoader.load(getClass().getResource("registration-window.fxml").openStream());
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
            stage.setTitle("Registration");
            stage.show();
            stage.setResizable(false);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getAdminButton() throws Exception{
        status = "admin";

        Stage stageToCloseAdmin = (Stage) btnAdmin.getScene().getWindow();
        stageToCloseAdmin.close();

        Pane root = fxmlLoader.load(getClass().getResource("authorization-window.fxml").openStream());
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Authorization");
        stage.setResizable(false);
        stage.show();
    }

    public void getExecutorButton() throws Exception{
        status = "executor";

        Stage stageToCloseExecutor = (Stage) btnExecutor.getScene().getWindow();
        stageToCloseExecutor.close();

        Pane root = fxmlLoader.load(getClass().getResource("authorization-window.fxml").openStream());
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Authorization");
        stage.setResizable(false);
        stage.show();
    }

    public void getClientButton() throws Exception{
        status = "client";

        Stage stageToCloseClient = (Stage) btnClient.getScene().getWindow();
        stageToCloseClient.close();

        Pane root = fxmlLoader.load(getClass().getResource("authorization-window.fxml").openStream());
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Authorization");
        stage.setResizable(false);
        stage.show();
    }

    public void toLogin() throws Exception{

        if(status.equals("admin") && authLogin.getText().equals("admin")){
            if(checkPassword(authLogin.getText(),authPassword.getText(), "administrator-window.fxml", 700, 400)){
                stage.setTitle("Table");
                Stage stageToCloseClient = (Stage) authButton.getScene().getWindow();
                stageToCloseClient.close();
            }
            else JOptionPane.showMessageDialog(null, "Ошибка авторизации!");
        }

        else if(status.equals("executor") && authLogin.getText().equals("executor")){
            if(checkPassword(authLogin.getText(),authPassword.getText(), "executor.fxml", 800, 500)){
                stage.setTitle("Executor");
                Stage stageToCloseClient = (Stage) authButton.getScene().getWindow();
                stageToCloseClient.close();
            }
            else JOptionPane.showMessageDialog(null, "Ошибка авторизации!");
        }

        else if(status.equals("client")){
            if(checkPassword(authLogin.getText(),authPassword.getText(), "user-request-form-window.fxml", 700, 400)){
                stage.setTitle("Requests Form");
                Stage stageToCloseClient = (Stage) authButton.getScene().getWindow();
                stageToCloseClient.close();
            }
            else JOptionPane.showMessageDialog(null, "Ошибка авторизации!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Ошибка авторизации!");
        }
    }
    public boolean checkPassword(String login, String password, String window, int v, int v1) throws Exception{

        if(login.isEmpty() | password.isEmpty()){
            return false;
        }
        else{
            String realPassword = "";
            connection = Connector.ConnectDb();
            String sql = String.format("select Пароль from accounts where Логин='%s'", login);
            resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                realPassword = resultSet.getString("Пароль");
            }
            if(realPassword.equals(authPassword.getText())){
                Pane root = fxmlLoader.load(getClass().getResource(window).openStream());
                Scene scene = new Scene(root, v, v1);
                stage.setScene(scene);
                stage.setTitle("Authorization");
                stage.show();
                stage.setResizable(false);
                return true;
            }
        }
        return false;
    }

    public void goBack() throws Exception{
        Stage stageCLose = (Stage) backButton.getScene().getWindow();
        stageCLose.close();

        Pane root = fxmlLoader.load(getClass().getResource("role.fxml").openStream());
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Role");
        stage.show();
        stage.setResizable(false);
    }
}
