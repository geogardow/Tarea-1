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
import java.io.*
import java.awt.event.*;
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
    public class sendData implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent e){
            try {
                Socket socketClient = new Socket("192.168.0.4", 9999);
                infoPack data= new infoPack();
                data.setPeso(50);
                data.setValor(41);
                data.setImpuesto(50);
                ObjectOutputStream outPack = new ObjectOutputStream(socketClient.getOutputStream());
                outPack.writeObject(data);
                socketClient.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        public void main(String[] args) throws IOException {
            try {
                ServerSocket socketServerClient = new ServerSocket(9090);
                infoPack receivedPack;
                while (true) {
                    Socket socketClient = socketServerClient.accept();
                    ObjectInputStream inPack = new ObjectInputStream(socketClient.getInputStream());
                    receivedPack = (infoPack) inPack.readObject();
                    int peso, valor, impuesto;
                    peso=receivedPack.getPeso();
                    valor=receivedPack.getValor();
                    impuesto=receivedPack.getImpuesto();
                    double monto;
                    monto=((valor*(impuesto/100))+(peso*0.15));
                    finalPack data= new finalPack();
                    data.setMonto(monto);
                    Socket sendClient1 = new Socket("192.168.0.4",9999);
                    ObjectOutputStream answerPack= new ObjectOutputStream(sendClient1.getOutputStream());
                    answerPack.writeObject(data);
                    sendClient1.close();
                    socketClient.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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