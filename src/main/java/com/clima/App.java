package com.clima;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa ciudades separadas por coma: ");
        String input = scanner.nextLine();

        String[] ciudades = input.split(",");

        for (String ciudad : ciudades) {

            String ciudadLimpia = ciudad.trim(); 

            if (!ciudadLimpia.isEmpty()) { // VALIDACION EXTRA
                System.out.println("\n=========================");
                WeatherService.obtenerClima(ciudadLimpia);
            }
        }
        scanner.close();
    }
}
