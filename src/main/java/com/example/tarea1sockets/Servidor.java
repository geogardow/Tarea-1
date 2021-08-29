package com.example.tarea1sockets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

/**
 * Clase Servidor
 *
 * Clase que contiene los procesos del servidor y del cliente 2
 *
 * @author Geovanny García Downing
 * @version 1.0
 */
public class Servidor {
    /**
     * Interfaz
     */
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
    /**
     * Clase sendData
     *
     * Clase que contiene la acción a ejecutar al presionar el botón
     *
     * @author Geovanny García Downing
     * @version 1.0
     */
    static class sendData implements ActionListener{
        /**
         * Está a la espera de la presión del botón
         * @param e
         */
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