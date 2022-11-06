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

public class ServicesController implements Initializable {

    @FXML TableView<Services> table;
    @FXML TableColumn<Services, String> col_id;
    @FXML TableColumn<Services, String> col_type;
    @FXML TableColumn<Services, String> col_name;
    @FXML TableColumn<Services, String> col_workTime;
    @FXML TableColumn<Services, String> col_time;
    @FXML TableColumn<Services, String> col_price;

    @FXML TextField id;
    @FXML TextField type;
    @FXML TextField name;
    @FXML TextField workTime;
    @FXML TextField time;
    @FXML TextField price;
    @FXML
    Button backButton;

    ObservableList<Services> list = FXCollections.observableArrayList();
    Connection connection;

    public void toDelete() throws Exception{
        connection = Connector.ConnectDb();
        String id1 = id.getText();
        String sql = String.format("DELETE from allservices WHERE id='%s'", id1); // Удаляет всё, где ид равен выбранному ид
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        JOptionPane.showMessageDialog(null, "Запись удалена!");
        toDisplay();
    }

    public void toAdd() throws Exception{

        String id1 = id.getText();
        String type1 = type.getText();
        String name1 = name.getText();
        String workTime1 = workTime.getText();
        String time1 = time.getText();
        String price1 = price.getText();

        if(type1.isEmpty() | name1.isEmpty() | workTime1.isEmpty() | time1.isEmpty() | price1.isEmpty() | id1.isEmpty()){ // Проверка, что строки не пустые
            JOptionPane.showMessageDialog(null, "Ошибка обновления данных");
        }


        else{ // помещает в базу данных новые значения
            connection = Connector.ConnectDb();
            PreparedStatement statement = connection.prepareStatement("insert into allservices " +
                    "(id,Вид_услуги, Наименование_услуги, Срок_службы, Срок_выполнения, Цена) " +
                    "values (?, ?, ?, ?, ?, ?)");

            statement.setString(1, id1);
            statement.setString(2, type1);
            statement.setString(3, name1);
            statement.setString(4, workTime1);
            statement.setString(5, time1);
            statement.setString(6, price1);

            statement.execute();

            JOptionPane.showMessageDialog(null, "Запись добавлена!");

            toDisplay();
        }
    }

    public void toUpdate() throws Exception{

            connection = Connector.ConnectDb();

            String id1 = id.getText();
            String type1 = type.getText();
            String name1 = name.getText();
            String workTime1 = workTime.getText();
            String time1 = time.getText();
            String price1 = price.getText();

            if(id1.isEmpty() | type1.isEmpty() | name1.isEmpty() | workTime1.isEmpty() | time1.isEmpty() | price1.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ошибка обновления данных");
            }

            else { // Обновляет значение ниже, где ид равен выбранному
                String sql = String.format("update allservices set Вид_услуги= '%s',Наименование_услуги= '%s', " +
                                "Срок_службы= '%s', Срок_выполнения= '%s', Цена= '%s'  where id='%s'", type1, name1, workTime1,
                        time1, price1, id1);

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                JOptionPane.showMessageDialog(null, "Запись обновлена!");
                toDisplay();
            }

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
        ResultSet rs = connection.createStatement().executeQuery("select * from allservices");

        while (rs.next()) {
            list.add(new Services(Integer.parseInt(rs.getString(1)),
                    rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)));
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_workTime.setCellValueFactory(new PropertyValueFactory<>("workTime"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(list);
    }

    public void getSelected() {
        try{
            int index = table.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            id.setText(String.valueOf(col_id.getCellData(index)));
            type.setText(col_type.getCellData(index).toString());
            name.setText(col_name.getCellData(index).toString());
            workTime.setText(col_workTime.getCellData(index).toString());
            time.setText(col_time.getCellData(index).toString());
            price.setText(col_price.getCellData(index).toString());

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
        stage.setTitle("Table");
        stage.show();
        stage.setResizable(false);
    }
}
