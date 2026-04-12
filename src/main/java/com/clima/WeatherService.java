package com.clima;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class WeatherService {

    // 🧠 Caché en memoria
    private static final Map<String, CacheItem> cache = new HashMap<>();
    private static final long CACHE_DURATION = 60 * 60 * 1000; // 1 hora

    /**
     * Obtiene y muestra el clima actual de una ciudad utilizando la API de Open-Meteo.
     */
    public static void obtenerClima(String ciudad) {

        if (ciudad == null || ciudad.trim().isEmpty()) {
            System.out.println("Debes ingresar una ciudad válida ❌");
            return;
        }

        try {
            String cacheKey = "weather_" + ciudad.toLowerCase();

            // 🔹 1. Revisar caché
            String cachedData = getFromCache(cacheKey);
            JSONObject climaJson;

            if (cachedData != null) {
                System.out.println("⚡ Usando datos en caché...");
                climaJson = new JSONObject(cachedData);
            } else {
                System.out.println("🌐 Consultando API...");

                String ciudadCodificada = URLEncoder.encode(ciudad.trim(), StandardCharsets.UTF_8);

                // 🔹 Geocoding
                String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + ciudadCodificada;
                String geoResponse = getApiResponse(geoUrl);

                if (geoResponse == null) return;

                JSONObject geoJson = new JSONObject(geoResponse);

                if (!geoJson.has("results") || geoJson.getJSONArray("results").isEmpty()) {
                    System.out.println("Ciudad no encontrada ❌");
                    return;
                }

                JSONObject location = geoJson.getJSONArray("results").getJSONObject(0);
                double latitud = location.getDouble("latitude");
                double longitud = location.getDouble("longitude");

             // 🔹 Clima optimizado (pronóstico)
                String climaUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                        + latitud + "&longitude=" + longitud
                        + "&daily=temperature_2m_max,temperature_2m_min,weathercode"
                        + "&timezone=auto";

                String climaResponse = getApiResponse(climaUrl);

                if (climaResponse == null) return;

                climaJson = new JSONObject(climaResponse);

                // 🔹 Guardar en caché
                saveToCache(cacheKey, climaResponse);
                }

                // 🔹 VALIDAR DAILY (ya no current ❗)
                if (!climaJson.has("daily")) {
                    System.out.println("La API no devolvió datos del clima ❌");
                    return;
                }

                // 🔹 Obtener datos del pronóstico
                JSONObject daily = climaJson.getJSONObject("daily");

                var fechas = daily.getJSONArray("time");
                var tempMax = daily.getJSONArray("temperature_2m_max");
                var tempMin = daily.getJSONArray("temperature_2m_min");
                var codigos = daily.getJSONArray("weathercode");

                System.out.println("\nPronóstico en " + ciudad + ":");

                // 🔹 Mostrar 3 a 5 días
                for (int i = 0; i < Math.min(5, fechas.length()); i++) {
                    String fecha = fechas.getString(i);
                    double max = tempMax.getDouble(i);
                    double min = tempMin.getDouble(i);
                    int codigo = codigos.getInt(i);

                    String descripcion = traducirCodigoClima(codigo);

                    System.out.println(fecha + " | Max: " + max + "°C | Min: " + min + "°C | " + descripcion);
                }
            } catch (Exception e) {
            System.out.println("Ocurrió un error al procesar la información ❌");
        }
    }

    /**
     * Realiza una solicitud HTTP GET a una URL.
     */
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

            StringBuilder response = new StringBuilder();
            String line;

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

    /**
     * Guarda datos en caché.
     */
    private static void saveToCache(String key, String data) {
        cache.put(key, new CacheItem(data, System.currentTimeMillis()));
    }

    /**
     * Obtiene datos desde caché si aún son válidos.
     */
    private static String getFromCache(String key) {
        CacheItem item = cache.get(key);

        if (item == null) return null;

        long ahora = System.currentTimeMillis();

        if (ahora - item.timestamp < CACHE_DURATION) {
            return item.data;
        }

        return null;
    }

    /**
     * Traduce códigos del clima a texto.
     */
    private static String traducirCodigoClima(int codigo) {
        return switch (codigo) {
            case 0 -> "Despejado ☀️";
            case 1, 2, 3 -> "Parcialmente nublado ⛅";
            case 45, 48 -> "Niebla 🌫️";
            case 51, 53, 55 -> "Llovizna 🌧️";
            case 61, 63, 65 -> "Lluvia 🌧️";
            case 71, 73, 75 -> "Nieve ❄️";
            case 80, 81, 82 -> "Chubascos 🌧️";
            case 95 -> "Tormenta ⛈️";
            default -> "Clima desconocido (" + codigo + ")";
        };
    }

    /**
     * Clase interna para manejar caché.
     */
    private static class CacheItem {
        String data;
        long timestamp;

        CacheItem(String data, long timestamp) {
            this.data = data;
            this.timestamp = timestamp;
        }
    }
}