package com.example.kursovoy_project;

public class Services {
    int id;
    String type, name, workTime, time, price;

    public Services(int id, String type, String name, String workTime, String time, String price){
        this.id = id;
        this.type = type;
        this.name = name;
        this.workTime = workTime;
        this.time = time;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getWorkTime() {
        return workTime;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getTime(){return time;}
}
