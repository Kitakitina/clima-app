package com.clima;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa una ciudad: ");
        String ciudad = scanner.nextLine();

        try {
            // 1. Obtener coordenadas
            String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + ciudad;
            String geoResponse = getApiResponse(geoUrl);

            JSONObject geoJson = new JSONObject(geoResponse);
            if (!geoJson.has("results")) {
                System.out.println("Ciudad no encontrada X");
                return ;
                }

            JSONObject location = geoJson.getJSONArray("results").getJSONObject(0);
            double lat = location.getDouble("latitude");
            double lon = location.getDouble("longitude");

            // 2. Obtener clima
            String climaUrl = "https://api.open-meteo.com/v1/forecast?latitude=" 
                    + lat + "&longitude=" + lon + "&current_weather=true";

            String climaResponse = getApiResponse(climaUrl);

            JSONObject climaJson = new JSONObject(climaResponse);
            JSONObject current = climaJson.getJSONObject("current_weather");

            double temperatura = current.getDouble("temperature");
            double viento = current.getDouble("windspeed");

            int codigo = current.getInt("weathercode");

            String descripcion = switch (codigo) {
                case 0 -> "Despejado ☀️";
                case 1, 2, 3 -> "Parcialmente nublado ⛅";
                case 45, 48 -> "Niebla 🌫️";
                case 51, 53, 55 -> "Llovizna 🌧️";
                case 61, 63, 65 -> "Lluvia 🌧️";
                case 71, 73, 75 -> "Nieve ❄️";
                default -> "Clima desconocido";
            };

            System.out.println("\nClima en " + ciudad + ":");
            System.out.println("Temperatura: " + temperatura + "°C");
            System.out.println("Viento: " + viento + " km/h");
            System.out.println("Condición: " + descripcion);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static String getApiResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    }
}
