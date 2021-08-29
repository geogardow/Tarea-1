package com.example.tarea1sockets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase HelloApplication
 *
 * Clase que contiene los procesos de la aplicación del cliente
 *
 * @author Geovanny García Downing
 * @version 1.0
 */
public class HelloApplication {
    /**
     * Interfaz
     */
    public static graphicInt clienteGUI;

    public static void main(String[] args) throws IOException {
        sendData enviar = new sendData();
        clienteGUI = new graphicInt("Cliente 1", enviar);
        int peso, valor, impuesto;
        double monto = 0.0;
        try {
            ServerSocket socketServer = new ServerSocket(9999);
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
                    System.out.println("Enviado desde Cliente 1");
                    sendClient.close();
                }
                else{
                    System.out.println("Recibido de Cliente 2");
                    monto=receivedPack.getMonto();
                    System.out.println(monto);
                    clienteGUI.montoText.setText(String.valueOf(monto));
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
     * Clase que contiene la acción que se ejecuta al presionar el botón
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
            if (e.getSource() == clienteGUI.enviarButton) {
                try{
                    int peso, valor, impuesto;
                    peso=Integer.parseInt(clienteGUI.pesoBox.getText());
                    valor=Integer.parseInt(clienteGUI.valorBox.getText());
                    impuesto=Integer.parseInt(clienteGUI.impBox.getText());
                    Socket socketClient = new Socket("localhost", 9090);
                    infoPack data = new infoPack();
                    data.setPeso(peso);
                    data.setValor(valor);
                    data.setImpuesto(impuesto);
                    data.setPuerto(9999);
                    data.setMonto(-1.0);
                    ObjectOutputStream outPack = new ObjectOutputStream(socketClient.getOutputStream());
                    outPack.writeObject(data);
                    socketClient.close();
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Sólo se permite el ingreso de enteros");
                    clienteGUI.pesoBox.setText(null);
                    clienteGUI.valorBox.setText(null);
                    clienteGUI.impBox.setText(null);
                }
            }
        }
    }
}
/**
 * Clase infoPack
 *
 * Clase que permite empacar los datos a enviar en un objeto
 *
 * @author Geovanny García Downing
 * @version 1.0
 */
class infoPack implements Serializable {
    private int peso, valor, impuesto, puerto;
    private double monto;

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

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

