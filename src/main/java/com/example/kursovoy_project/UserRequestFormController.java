package com.example.kursovoy_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class UserRequestFormController implements Initializable {

    Connection connection;
    @FXML
    ComboBox<String> choiceOfServicesList;
    @FXML TextField requestFormName;
    @FXML TextField requestFormSurname;
    @FXML TextField requestFormNumber;
    @FXML TextField requestFormAddress;
    @FXML Button outButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collection<String> list = new ArrayList<String>();
        String sql = String.format("select * from allservices");
        try{
            connection = Connector.ConnectDb();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                String price = resultSet.getString("Цена");
                String name = resultSet.getString("Наименование_услуги");
                list.add(name + " " + price);
            }
           choiceOfServicesList.getItems().addAll(list);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void toRequest() throws Exception{
        String name = requestFormName.getText();
        String surname = requestFormSurname.getText();
        String number = requestFormNumber.getText();
        String address = requestFormAddress.getText();

        if(name.isEmpty() | surname.isEmpty() | number.isEmpty() | address.isEmpty()){
            try{
                String nameService = choiceOfServicesList.getSelectionModel().getSelectedItem().toString();
                JOptionPane.showMessageDialog(null, "Ошибка. Все поля должны быть заполнены.");
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Ошибка. Все поля должны быть заполнены.");
            }
        }
        else{
            try{
                String nameService = choiceOfServicesList.getSelectionModel().getSelectedItem().toString();

                String typeOfService = "";
                String find = nameOfService(nameService);

                connection = Connector.ConnectDb();
                String sql = String.format("select Вид_услуги from group.allservices where Наименование_услуги='%s'", find);
                ResultSet resultSet = connection.createStatement().executeQuery(sql);
                while (resultSet.next()){
                    typeOfService = resultSet.getString("Вид_услуги");
                }

                PreparedStatement preparedStatement = connection.prepareStatement("insert into clientservices " +
                        "(Имя, Фамилия, Вид_услуги, Наименование_услуги, Номер_телефона_клиента, Статус_выполнения, Адрес) " +
                        "values (?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, typeOfService);
                preparedStatement.setString(4, find);
                preparedStatement.setString(5, number);
                preparedStatement.setString(6, "Принято");
                preparedStatement.setString(7, address);

                preparedStatement.execute();

                JOptionPane.showMessageDialog(null, "Заявка подана!");
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Ошибка. Все поля должны быть заполнены.");
            }
        }
    }

    public String nameOfService(String text){
        String[] words = text.split(" ");
        String toReturn = "";
        for(int i = 0; i < words.length - 2; i++){
            toReturn += words[i] + " ";
        }
        return toReturn.trim();
    }

    public void goOut() {
        System.exit(1);
    }
}
