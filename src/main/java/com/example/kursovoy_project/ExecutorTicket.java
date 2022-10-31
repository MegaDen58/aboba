package com.example.kursovoy_project;

public class ExecutorTicket {
    int id;
    String address, type, name, number, status;

    public ExecutorTicket(int id, String address, String type, String name, String number, String status){
        this.id = id;
        this.address = address;
        this.type = type;
        this.name = name;
        this.number = number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }
}
