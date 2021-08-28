package com.example.tarea1sockets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class Servidor {
    public static graphicInt servidorGUI;

    public static void main(String[] args) {
        sendData enviar = new sendData();
        servidorGUI = new graphicInt("Cliente 2", enviar);
        int peso, valor, impuesto;
        double monto = 0.0;
        try {
            ServerSocket socketServer = new ServerSocket(9090);
            infoPack receivedPack;
            while (true) {
                Socket socketC = socketServer.accept();
                ObjectInputStream inPack = new ObjectInputStream(socketC.getInputStream());
                receivedPack = (infoPack) inPack.readObject();
                if (receivedPack.getMonto()<0){
                    peso = receivedPack.getPeso();
                    valor = receivedPack.getValor();
                    impuesto = receivedPack.getImpuesto();
                    monto = ((valor * (impuesto / 100)) + (peso * 0.15));
                    receivedPack.setMonto(monto);
                    Socket sendClient = new Socket("localhost", receivedPack.getPuerto());
                    ObjectOutputStream answerPack = new ObjectOutputStream(sendClient.getOutputStream());
                    answerPack.writeObject(receivedPack);
                    System.out.println("Enviado desde Cliente 2");
                    sendClient.close();

                }
                else{
                    System.out.println("Recibido de Cliente 1");
                    monto=receivedPack.getMonto();
                    System.out.println(monto);
                    servidorGUI.montoText.setText(String.valueOf(monto));
                }
                socketC.close();
            }
        } catch (Exception n) {
            System.out.println(n.getMessage());
        }
    }

    static class sendData implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == servidorGUI.enviarButton) {
                try{
                    int peso, valor, impuesto;
                    peso=Integer.parseInt(servidorGUI.pesoBox.getText());
                    valor=Integer.parseInt(servidorGUI.valorBox.getText());
                    impuesto=Integer.parseInt(servidorGUI.impBox.getText());
                    Socket socketClient = new Socket("localhost", 9999);
                    infoPack data = new infoPack();
                    data.setPeso(peso);
                    data.setValor(valor);
                    data.setImpuesto(impuesto);
                    data.setPuerto(9090);
                    data.setMonto(-1.0);
                    ObjectOutputStream outPack = new ObjectOutputStream(socketClient.getOutputStream());
                    outPack.writeObject(data);
                    socketClient.close();
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Sólo se permite el ingreso de enteros");
                    servidorGUI.pesoBox.setText(null);
                    servidorGUI.valorBox.setText(null);
                    servidorGUI.impBox.setText(null);
                }
            }
        }
    }
}
//        infoPack receivedPack;
//        while (true) {
//        Socket socketC = socketServer.accept();
//        System.out.println("Recibiendo");
//        ObjectInputStream inPack = new ObjectInputStream(socketC.getInputStream());
//        receivedPack = (infoPack) inPack.readObject();
//        peso=receivedPack.getPeso();
//        valor=receivedPack.getValor();
//        impuesto=receivedPack.getImpuesto();
//        monto=((valor*(impuesto/100))+(peso*0.15));
//        System.out.println(monto);
//        infoPack data= new infoPack();
//        data.setMonto(monto);
//        Socket sendClient = new Socket("192.168.0.7",9999);
//        ObjectOutputStream answerPack= new ObjectOutputStream(sendClient.getOutputStream());
//        answerPack.writeObject(data);
//        System.out.println("Paquete recibido");
//        sendClient.close();
//        socketC.close();
//    int peso, valor, impuesto;
//    double monto;
//        try{
//                peso= servidorGUI.peso;
//                valor= servidorGUI.valor;
//                impuesto= servidorGUI.impuesto;
//                System.out.println(50);
//                } catch (Exception e) {
//                System.out.println("Sólo se permite la entrada de enteros. A continuación se muestra el error"+e);;
//                }

//try {

//        }
//        } catch (ClassNotFoundException | IOException e) {
//        System.out.println("Paquete no recibido");
//
//        }