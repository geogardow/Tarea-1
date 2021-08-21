package com.example.tarea1sockets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.awt.event.*;
import java.net.*;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    private class sendData implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            try {
                Socket socketClient = new Socket("192.168.0.4", 9999);
                DataOutputStream infoStream = new DataOutputStream(socketClient.getOutputStream());
                infoStream.writeUTF("PRUEBA");
                infoStream.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}