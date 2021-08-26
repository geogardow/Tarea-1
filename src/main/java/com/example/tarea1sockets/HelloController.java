package com.example.tarea1sockets;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HelloController {
    static int peso, valor, impuestos;
    public void exitButton (MouseEvent mouseEvent){
        Platform.exit();
        System.exit(0);
    }
    public TextField pesoBox;
    public TextField valorBox;
    public TextField impuestosBox;

    public void sendButton (MouseEvent mouseEvent){
        try{
            sendData mensaje= new sendData();
            peso= Integer.parseInt(pesoBox.getText());
            valor= Integer.parseInt(valorBox.getText());
            impuestos=Integer.parseInt(impuestosBox.getText());
            System.out.println(peso);
            mensaje.end();
        } catch (NumberFormatException e) {
            System.out.println("Error de tipo de variable");
        }

    }

}