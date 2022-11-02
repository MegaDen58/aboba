package com.example.kursovoy_project;

public class Requests {

    int id;
    String name, surname, type, nameService, number, status, address, access;

    public Requests(int id, String name, String surname, String type, String nameService, String number, String status, String address, String access){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.nameService = nameService;
        this.number = number;
        this.status = status;
        this.address = address;
        this.access = access;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getType() {
        return type;
    }

    public String getNameService() {
        return nameService;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getAccess() {
        return access;
    }
}
