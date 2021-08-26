package com.example.tarea1sockets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphicInt extends JFrame implements Runnable {
    private JPanel mainPanel;
    private JButton enviarButton;
    private JTextField impBox;
    private JTextField pesoBox;
    private JTextField valorBox;
    private JTextField montoBox;

    public JButton getEnviarButton(ActionListener enviar) {
        enviarButton.addActionListener(enviar);
        return enviarButton;
    }

    public void setEnviarButton(JButton enviarButton) {
        this.enviarButton = enviarButton;
    }

    public JTextField getImpBox() {
        return impBox;
    }

    public void setImpBox(JTextField impBox) {
        this.impBox = impBox;
    }

    public JTextField getPesoBox() {
        return pesoBox;
    }

    public void setPesoBox(JTextField pesoBox) {
        this.pesoBox = pesoBox;
    }

    public JTextField getValorBox() {
        return valorBox;
    }

    public void setValorBox(JTextField valorBox) {
        this.valorBox = valorBox;
    }

    public JTextField getMontoBox() {
        return montoBox;
    }

    public void setMontoBox(JTextField montoBox) {
        this.montoBox = montoBox;
    }

    public graphicInt(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(500, 500));
        this.setResizable(false);
        this.pack();
    }

    public static void main(String[] args) {

    }

    public void run() {

    }
}
