package com.clima;

import javax.swing.*;

public class AppGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Clima 🌤️");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField input = new JTextField();
        JButton boton = new JButton("Buscar");

        boton.addActionListener(e -> {
            String ciudad = input.getText();
            WeatherService.obtenerClima(ciudad);
        });

        frame.setLayout(null);

        input.setBounds(50, 30, 200, 30);
        boton.setBounds(260, 30, 80, 30);

        frame.add(input);
        frame.add(boton);

        frame.setVisible(true);
    }
}
