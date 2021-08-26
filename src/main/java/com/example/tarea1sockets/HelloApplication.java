package com.example.tarea1sockets;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;

public class HelloApplication extends Application {
    private double xOffset;
    private double yOffset;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() - xOffset);
                stage.setY(mouseEvent.getScreenY() - yOffset);
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
}
class sendData {
    public boolean end() {
        try {
            Socket socketClient = new Socket("192.168.0.8", 9090);
            infoPack data = new infoPack();
            data.setPeso(HelloController.peso);
            data.setValor(HelloController.valor);
            data.setImpuesto(HelloController.impuestos);
            ObjectOutputStream outPack = new ObjectOutputStream(socketClient.getOutputStream());
            outPack.writeObject(data);
            socketClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ServerSocket socketServerClient = new ServerSocket(9999);
            finalPack receivedPack;
            while (true) {
                Socket socketClientRe = socketServerClient.accept();
                ObjectInputStream inPack = new ObjectInputStream(socketClientRe.getInputStream());
                receivedPack = (finalPack) inPack.readObject();
                System.out.println(receivedPack.getMonto());
                socketClientRe.close();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return true;
        }
    }
}



class infoPack implements Serializable {
    private int peso, valor, impuesto;

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }
}

class finalPack implements Serializable{
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    private double monto;


}