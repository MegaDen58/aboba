package com.example.kursovoy_project;

public class ExecutorRequests {
    int id;
    String address, type, name, status;

    public ExecutorRequests(int id, String address, String type, String name, String status){
        this.id = id;
        this.address = address;
        this.type = type;
        this.name = name;
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

    public String getStatus() {
        return status;
    }
}
