package com.example.tarea1sockets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase graphicInt
 *
 * Clase que contiene la interfaz gráfica
 *
 * @author Geovanny García Downing
 * @version 1.0
 */
public class graphicInt extends JFrame{
    /**
     * Panel proncipal
     */
    public JPanel mainPanel;
    /**
     * Texto que dice valor
     */
    public JLabel valorText;
    /**
     * Texto que dice peso
     */
    public JLabel pesoText;
    /**
     * Texto que dice impuesto
     */
    public JLabel impText;
    /**
     * Texto que dice monto
     */
    public JLabel montoText;
    /**
     * Caja para ingresar valor
     */
    public JTextField valorBox;
    /**
     * Caja para ingresar peso
     */
    public JTextField pesoBox;
    /**
     * Caja para ingresar impuesto
     */
    public JTextField impBox;
    /**
     * Botón de enviar
     */
    public JButton enviarButton;

    /**
     * Constructor con 2 parametros
     * @param title título a mostrar
     * @param e acción a ejecutar al presionar el botón
     */
    public graphicInt(String title, ActionListener e){

        setTitle(title);
        setSize(500,500);
        mainPanel = new JPanel();
        this.getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        enviarButton = new JButton("Enviar");
        enviarButton.setSize(100,30);
        enviarButton.setLocation(200,300);
        enviarButton.addActionListener(e);
        mainPanel.add(enviarButton);

        montoText = new JLabel("Monto");
        montoText.setBounds(235, 350, 200,40);
        mainPanel.add(montoText);

        valorBox = new JTextField();
        valorBox.setBounds(250,50,100,25);
        mainPanel.add(valorBox);

        pesoBox = new JTextField();
        pesoBox.setBounds(250,125,100,25);
        mainPanel.add(pesoBox);

        impBox = new JTextField();
        impBox.setBounds(250,200,100,25);
        mainPanel.add(impBox);

        valorText = new JLabel("Precio: ");
        valorText.setBounds(175,50,70,25);
        mainPanel.add(valorText);

        pesoText = new JLabel("Peso: ");
        pesoText.setBounds(175,125,70,25);
        mainPanel.add(pesoText);

        impText = new JLabel("Impuesto: ");
        impText.setBounds(175,200,70,25);
        mainPanel.add(impText);

        setResizable(false);
        mainPanel.repaint();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
