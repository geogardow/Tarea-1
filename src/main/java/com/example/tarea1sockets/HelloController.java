package com.example.tarea1sockets;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {
    public void exitButton (MouseEvent mouseEvent){
        Platform.exit();
        System.exit(0);
    }
    public void pesoBox (){

    }

}