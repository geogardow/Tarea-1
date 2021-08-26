package com.example.tarea1sockets;

import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) {
        graphicInt servidorGUI= new graphicInt("Servidor");
        servidorGUI.setVisible(true);
        int peso, valor, impuesto;
        double monto;
        try{
            peso=Integer.parseInt(servidorGUI.getPesoBox().getText());
            valor=Integer.parseInt(servidorGUI.getValorBox().getText());
            impuesto=Integer.parseInt(servidorGUI.getImpBox().getText());
            System.out.println(peso);
        } catch (Exception e) {
            System.out.println("Sólo se permite la entrada de enteros. A continuación se muestra el error"+e);;
        }
        try {
            ServerSocket socketServer = new ServerSocket(9090);
            infoPack receivedPack;
            while (true) {
                Socket socketC = socketServer.accept();
                System.out.println("Recibiendo");
                ObjectInputStream inPack = new ObjectInputStream(socketC.getInputStream());
                receivedPack = (infoPack) inPack.readObject();
                peso=receivedPack.getPeso();
                valor=receivedPack.getValor();
                impuesto=receivedPack.getImpuesto();
                monto=((valor*(impuesto/100))+(peso*0.15));
                System.out.println(monto);
                infoPack data= new infoPack();
                data.setMonto(monto);
                Socket sendClient = new Socket("192.168.0.7",9999);
                ObjectOutputStream answerPack= new ObjectOutputStream(sendClient.getOutputStream());
                answerPack.writeObject(data);
                System.out.println("Paquete recibido");
                sendClient.close();
                socketC.close();
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Paquete no recibido");

        }
    }
}
