package com.example.kursovoy_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdministratorController {

   @FXML Button accounts;
    @FXML Button services;
    @FXML Button tickets;
    @FXML Button backButton;


    public void toServices() throws Exception{
        Stage stageToCloseClient = (Stage) services.getScene().getWindow();
        stageToCloseClient.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("services.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Accounts");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toAccounts() throws Exception{
        Stage stageToCloseClient = (Stage) accounts.getScene().getWindow();
        stageToCloseClient.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accounts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Accounts");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void toTickets() throws Exception{
        Stage stageToCloseClient = (Stage) tickets.getScene().getWindow();
        stageToCloseClient.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tickets.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        stage.setTitle("Accounts");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void goBack() throws Exception{
        Stage stageCLose = (Stage) backButton.getScene().getWindow();
        stageCLose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("role.fxml").openStream());
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Authorization");
        stage.show();
        stage.setResizable(false);
    }
}
