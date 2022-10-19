module com.example.kursovoy_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kursovoy_project to javafx.fxml;
    exports com.example.kursovoy_project;
}