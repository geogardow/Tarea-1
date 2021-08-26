package com.example.tarea1sockets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class HelloApplication {
    int peso, valor, impuesto;
    double monto;

    static graphicInt clienteGUI = new graphicInt("Cliente");
    sendDat EnviaDatos = new sendDat();
    JButton botonE = clienteGUI.getEnviarButton(EnviaDatos);


    public static void main(String[] args) {
        clienteGUI.setVisible(true);
    }

    private class sendDat implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonE){
                try {
                    peso = Integer.parseInt(clienteGUI.getPesoBox().getText());
                    valor = Integer.parseInt(clienteGUI.getValorBox().getText());
                    impuesto = Integer.parseInt(clienteGUI.getImpBox().getText());
                    System.out.println(peso);
                } catch (Exception n) {
                    System.out.println("Sólo se permite la entrada de enteros. A continuación se muestra el error" + n);
                    ;
                }
            }
        }
    }
}
class sendData {
    public boolean end() {
        try {
            Socket socketClient = new Socket("192.168.0.7", 9090);
            infoPack data = new infoPack();
            data.setPeso(50);
            data.setValor(50);
            data.setImpuesto(50);
            data.setMonto(0.0);
            ObjectOutputStream outPack = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream inPack = new ObjectInputStream(socketClient.getInputStream());
            outPack.writeObject(data);
            socketClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            return true;
        }
    }

class infoPack implements Serializable {
    private int peso, valor, impuesto;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

