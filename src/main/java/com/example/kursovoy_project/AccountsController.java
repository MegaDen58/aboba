package com.example.kursovoy_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {

    @FXML TableView<Users> table;
    @FXML TableColumn<Users,String> col_id;
    @FXML TableColumn<Users,String> col_login;
    @FXML TableColumn<Users,String> col_password;
    @FXML TableColumn<Users,String> col_name;
    @FXML TableColumn<Users,String> col_surname;
    @FXML TextField id;
    @FXML TextField login;
    @FXML TextField password;
    @FXML TextField name;
    @FXML TextField surname;
    @FXML
    Button backButton;


    ObservableList<Users> list = FXCollections.observableArrayList();
    Connection connection;
    public void toUpdate() throws Exception{
        try {
            connection = Connector.ConnectDb();;

            String id1 = id.getText();
            String login1 = login.getText();
            String password1 = password.getText();
            String name1 = name.getText();
            String surname1 = surname.getText();


            String sql = String.format("update accounts set Логин= '%s',Пароль= '%s', " +
                            "Имя= '%s'," +
                            "Фамилия= '%s' where id='%s'", login1,
                    password1, name1, surname1, id1);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Запись обновлена!");
            toDisplay();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }

    public void toDelete() throws Exception{
        connection = Connector.ConnectDb();
        String id1 = id.getText();
        String sql = String.format("DELETE from accounts WHERE id='%s'", id1);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        toDisplay();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            toDisplay();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void toDisplay() throws Exception {
        table.getItems().clear();
        connection = Connector.ConnectDb();
        ResultSet rs = connection.createStatement().executeQuery("select * from accounts");

        while (rs.next()) {
            list.add(new Users(Integer.parseInt(rs.getString(1)),
                    rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)));
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));

        table.setItems(list);
    }

    public void getSelected() {
        try{
            int index = table.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            id.setText(String.valueOf(col_id.getCellData(index)));
            login.setText(col_login.getCellData(index).toString());
            password.setText(col_password.getCellData(index).toString());
            name.setText(col_name.getCellData(index).toString());
            surname.setText(col_surname.getCellData(index).toString());

        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void goBack() throws Exception{
        Stage stageCLose = (Stage) backButton.getScene().getWindow();
        stageCLose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("administrator-window.fxml").openStream());
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Admin");
        stage.show();
        stage.setResizable(false);
    }
}
