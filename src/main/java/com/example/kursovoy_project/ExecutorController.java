package com.example.kursovoy_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ExecutorController implements Initializable {

    @FXML ComboBox<String> comboBoxx;
    @FXML TableView<ExecutorTicket> table;
    @FXML TableColumn<ExecutorTicket, Integer> col_id;
    @FXML TableColumn<ExecutorTicket, String> col_address;
    @FXML TableColumn<ExecutorTicket, String> col_type;
    @FXML TableColumn<ExecutorTicket, String> col_name;
    @FXML TableColumn<ExecutorTicket, String> col_number;
    @FXML TableColumn<ExecutorTicket, String> col_status;
    @FXML Button backButton;
    Connection connection;
    ObservableList<ExecutorTicket> list = FXCollections.observableArrayList();
    public static int id;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collection<String> list = new ArrayList<>();
        list.add("Принято");
        list.add("Выполняется");
        list.add("Завершено");

        comboBoxx.getItems().addAll(list);
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
        ResultSet rs = connection.createStatement().executeQuery("select * from clientservices where Проверено='Да'");

        while (rs.next()) {
            list.add(new ExecutorTicket(Integer.parseInt(rs.getString("id")), rs.getString("Адрес"),
                    rs.getString("Вид_услуги"), rs.getString("Наименование_услуги"),
                    rs.getString("Номер_телефона_клиента"), rs.getString("Статус_выполнения")));
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.setItems(list);
    }


    public void getSelected() {
        try{
            int index = table.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            id = col_id.getCellData(index);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void toUpdate() throws Exception{
        String newStatus = comboBoxx.getSelectionModel().getSelectedItem().toString();

        connection = Connector.ConnectDb();

        String sql = String.format("update clientservices set Статус_выполнения='%s' where id='%s'", newStatus, id);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        toDisplay();

        JOptionPane.showMessageDialog(null, "Статус обновлён!");
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
