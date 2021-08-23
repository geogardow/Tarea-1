package com.example.tarea1sockets;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket socketServer = new ServerSocket(9999);
            infoPack receivedPack;
            while (true) {
                Socket socketClient = socketServer.accept();
                ObjectInputStream inPack = new ObjectInputStream(socketClient.getInputStream());
                receivedPack = (infoPack) inPack.readObject();
                Socket sendClient2 = new Socket("192.168.0.4",9090);
                ObjectOutputStream resendPack= new ObjectOutputStream(sendClient2.getOutputStream());
                resendPack.writeObject(receivedPack);
                sendClient2.close();
                socketClient.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
