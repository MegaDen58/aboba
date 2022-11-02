package com.example.kursovoy_project;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    public static Connection ConnectDb() throws Exception{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/allservices", "root", "159753");
        return con;
    }

}
