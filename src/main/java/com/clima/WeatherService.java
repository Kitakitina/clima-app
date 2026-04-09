package com.clima;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class WeatherService {

    public static void obtenerClima(String ciudad) {

        // Validar entrada del usuario
        if (ciudad == null || ciudad.trim().isEmpty()) {
            System.out.println("Debes ingresar una ciudad válida ❌");
            return;
        }

        try {
            // Codificar ciudad para evitar problemas con espacios o caracteres especiales
            String ciudadCodificada = URLEncoder.encode(ciudad.trim(), StandardCharsets.UTF_8);

            // 1️⃣ Obtener coordenadas desde la API de geocodificación
            String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + ciudadCodificada;
            String geoResponse = getApiResponse(geoUrl);

            if (geoResponse == null) {
                System.out.println("No se pudo obtener información de la ciudad ❌");
                return;
            }

            JSONObject geoJson = new JSONObject(geoResponse);

            if (!geoJson.has("results") || geoJson.getJSONArray("results").isEmpty()) {
                System.out.println("Ciudad no encontrada ❌");
                return;
            }

            JSONObject location = geoJson.getJSONArray("results").getJSONObject(0);
            double latitud = location.getDouble("latitude");
            double longitud = location.getDouble("longitude");

            // 2️⃣ Obtener clima usando las coordenadas
            String climaUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                    + latitud + "&longitude=" + longitud + "&current_weather=true";

            String climaResponse = getApiResponse(climaUrl);

            if (climaResponse == null) {
                System.out.println("No se pudo obtener el clima desde la API ❌");
                return;
            }

            JSONObject climaJson = new JSONObject(climaResponse);

            if (!climaJson.has("current_weather")) {
                System.out.println("La API no devolvió datos del clima ❌");
                return;
            }

            JSONObject climaActual = climaJson.getJSONObject("current_weather");

            double temperatura = climaActual.optDouble("temperature", Double.NaN);
            double viento = climaActual.optDouble("windspeed", Double.NaN);
            int codigoClima = climaActual.optInt("weathercode", -1);

            String descripcion = traducirCodigoClima(codigoClima);

            // Mostrar resultados
            System.out.println("\nClima en " + ciudad + ":");
            System.out.println("Temperatura: " + temperatura + "°C");
            System.out.println("Viento: " + viento + " km/h");
            System.out.println("Condición: " + descripcion);

        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado al obtener el clima ❌");
        }
    }

    private static String getApiResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();

            if (status != 200) {
                System.out.println("Error en la API (HTTP " + status + ") ❌");
                return null;
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            return response.toString();

        } catch (IOException e) {
            System.out.println("No se pudo conectar con la API ❌");
            return null;
        }
    }

    private static String traducirCodigoClima(int codigo) {
        return switch (codigo) {
            case 0 -> "Despejado ☀️";
            case 1, 2, 3 -> "Parcialmente nublado ⛅";
            case 45, 48 -> "Niebla 🌫️";
            case 51, 53, 55 -> "Llovizna 🌧️";
            case 61, 63, 65 -> "Lluvia 🌧️";
            case 71, 73, 75 -> "Nieve ❄️";
            default -> "Clima desconocido";
        };
    }
}