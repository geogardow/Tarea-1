module com.example.tarea1sockets {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tarea1sockets to javafx.fxml;
    exports com.example.tarea1sockets;
}