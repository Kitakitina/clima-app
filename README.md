# 🌤️ Clima App (Java + Open-Meteo)

## 👩‍💻 Autora  
Camila Reyes  

---

## 📌 Resumen del Proyecto  
Clima App es una aplicación desarrollada en Java que permite consultar el clima de una ciudad ingresada por el usuario.

La aplicación incluye:

- Consulta del **clima actual**
- **Pronóstico de 3 a 5 días**
- **Sistema de caché** para mejorar rendimiento
- **Interfaz gráfica básica (Swing)**

Utiliza la API de Open-Meteo para:

- Obtener coordenadas (latitud y longitud)  
- Consultar datos meteorológicos  

El proyecto está estructurado con Maven.

---

## ⚙️ Instrucciones de Instalación  

### 1️⃣ Clonar el repositorio  
```bash
git clone https://github.com/Kitakitina/clima-app.git
```

### 2️⃣ Entrar al proyecto  
```bash
cd clima-app
```

### 3️⃣ Compilar el proyecto  
```bash
mvn clean install
```

---

## ▶️ Guía de Uso  

### 🔹 Ejecutar versión consola  
```bash
mvn exec:java -Dexec.mainClass="com.clima.App"
```

### 🔹 Ejecutar interfaz gráfica  
```bash
mvn exec:java -Dexec.mainClass="com.clima.AppGUI"
```

---

## 💻 Ejemplo de Resultados  

### Consola:
```bash
Ingresa una ciudad: Santiago

Clima en Santiago:
Temperatura: 27°C
Viento: 7 km/h
Condición: Despejado ☀️

Pronóstico:
2026-04-12 | Max: 27°C | Min: 12°C | Despejado ☀️
2026-04-13 | Max: 25°C | Min: 11°C | Parcialmente nublado ⛅
```

### Caché:
```bash
⚡ Usando datos en caché...
```

---

## 🚀 Funcionalidades  

- Consulta del clima actual  
- Pronóstico de varios días  
- Conversión de ciudad a coordenadas  
- Integración con Open-Meteo  
- Traducción de códigos meteorológicos  
- Caché en memoria (mejora rendimiento)  
- Interfaz gráfica simple  
- Validación de entrada  
- Manejo de errores  

---

## ⚠️ Manejo de Errores  

- ❌ Ciudad vacía  
- ❌ Ciudad no encontrada  
- ❌ Error de conexión  
- ❌ Respuesta inválida de la API  

Se muestran mensajes claros sin detener la aplicación.

---

## ⚡ Uso de Caché  

- Guarda resultados en memoria  
- Evita llamadas repetidas a la API  
- Expira después de 1 hora  

Ejemplo:
```bash
⚡ Usando datos en caché...
```

---

## 🌐 Información de la API  

### Geocoding API  
```bash
https://geocoding-api.open-meteo.com/v1/search?name=Santiago
```

### Weather API  

Clima actual:
```bash
&current=temperature_2m,windspeed_10m,weathercode
```

Pronóstico:
```bash
&daily=temperature_2m_max,temperature_2m_min,weathercode
```

Documentación: https://open-meteo.com/

---

## 📁 Estructura del Proyecto  

```
clima-app
│
├── pom.xml
├── README.md
├── .gitignore
│
└── src
    └── main
        └── java
            └── com
                └── clima
                    ├── App.java
                    ├── AppGUI.java
                    └── WeatherService.java
```

- `App.java` → consola  
- `AppGUI.java` → interfaz gráfica  
- `WeatherService.java` → lógica + API + caché  

---

## 🔮 Mejoras Futuras  

- Ubicación automática  
- Mejorar interfaz gráfica  
- Caché persistente  
- Historial de búsquedas  
- Soporte °C / °F  
- Tests con JUnit  

---

## 📄 Licencia  

Proyecto con fines educativos.

Curso:  
**AI Training for Software Developer Graduates v2 - 2026**
