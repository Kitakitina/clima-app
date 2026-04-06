package com.clima;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa una ciudad: ");
        String ciudad = scanner.nextLine();

        WeatherService.obtenerClima(ciudad); 

        scanner.close();
    }
}
