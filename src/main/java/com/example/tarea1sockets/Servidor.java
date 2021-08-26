package com.example.tarea1sockets;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket socketServer = new ServerSocket(9090);
            infoPack receivedPack;

            while (true) {
                Socket socketC = socketServer.accept();
                System.out.println("Recibiendo");
                ObjectInputStream inPack = new ObjectInputStream(socketC.getInputStream());
                receivedPack = (infoPack) inPack.readObject();
                int peso, valor, impuesto;
                peso=receivedPack.getPeso();
                valor=receivedPack.getValor();
                impuesto=receivedPack.getImpuesto();
                double monto;
                monto=((valor*(impuesto/100))+(peso*0.15));
                System.out.println(monto);
                finalPack data= new finalPack();
                data.setMonto(monto);
                Socket sendClient = new Socket("192.168.0.8",9999);
                ObjectOutputStream answerPack= new ObjectOutputStream(sendClient.getOutputStream());
                answerPack.writeObject(data);
                System.out.println("Paquete recibido");
                sendClient.close();
                socketC.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Paquete no recibido");

        }
    }
}
