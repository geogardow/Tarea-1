package com.example.tarea1sockets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataOutputStream;
import java.awt.event.*;
import java.net.*;


import java.io.IOException;

public class HelloApplication extends Application {

    private double xOffset;
    private double yOffset;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                xOffset= mouseEvent.getSceneX();
                yOffset= mouseEvent.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX()-xOffset);
                stage.setY(mouseEvent.getScreenY()-yOffset);
            }
        });
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tarea 1");
        scene.setFill(Color.TRANSPARENT);
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