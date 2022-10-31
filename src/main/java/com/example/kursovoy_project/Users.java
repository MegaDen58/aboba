package com.example.kursovoy_project;

public class Users {
    int id;
    String login, password, name, surname;


    public Users(int id, String login, String password, String name, String surname){
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;

    }

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String  getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

}
