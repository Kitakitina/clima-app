# 🌤️ Clima App (Java + Open-Meteo)

## 👩‍💻 Autora

**Camila Reyes**

---

# 📌 Resumen del Proyecto

Clima App es una aplicación de consola desarrollada en Java que permite consultar el clima actual de una ciudad ingresada por el usuario.

La aplicación utiliza la API de Open-Meteo para:

1. Obtener las coordenadas geográficas (latitud y longitud) de una ciudad mediante la API de geocodificación.
2. Consultar el clima actual usando esas coordenadas.
3. Mostrar información meteorológica como temperatura, velocidad del viento y condición del clima.

El proyecto fue desarrollado utilizando Maven para la gestión de dependencias y estructura del proyecto.

---

# ⚙️ Instrucciones de Instalación

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/Kitakitina/clima-app.git
```

### 2️⃣ Entrar al proyecto

```bash
cd clima-app
```

### 3️⃣ Compilar el proyecto

El proyecto utiliza Maven, por lo que debes tener Maven instalado.

```bash
mvn clean install
```

---

# ▶️ Guía de Uso

Para ejecutar la aplicación:

```bash
mvn exec:java -Dexec.mainClass="com.clima.App"
```

Luego el programa solicitará que ingreses una ciudad.

Ejemplo:

```
Ingresa una ciudad: Santiago
```

La aplicación consultará la API y mostrará el clima actual.

---

# 💻 Ejemplo de Resultados

Ejemplo de ejecución:

```
Ingresa una ciudad: Santiago

Clima en Santiago:
Temperatura: 22°C
Viento: 10 km/h
Condición: Despejado ☀️
```

---

# 🚀 Funcionalidades

* Consulta del clima actual por nombre de ciudad
* Conversión automática de ciudad a coordenadas (latitud y longitud)
* Integración con la API de Open-Meteo
* Traducción del código meteorológico a descripción legible
* Manejo de errores para entradas inválidas
* Validación de entrada del usuario
* Manejo de errores de conexión con la API
* Estructura modular separando lógica y aplicación (`WeatherService`)

---

# ⚠️ Manejo de Errores

La aplicación incluye varias validaciones para evitar fallos:

* ❌ Ciudad vacía o inválida
* ❌ Ciudad que no existe en la API
* ❌ Errores de conexión con la API
* ❌ Respuestas incompletas del servicio

En estos casos se muestran mensajes claros al usuario sin que el programa se detenga inesperadamente.

---

# 🌐 Información de la API

Esta aplicación utiliza la API gratuita de Open-Meteo.

### Geocoding API

Permite obtener latitud y longitud a partir de un nombre de ciudad.

Ejemplo de endpoint:

```
https://geocoding-api.open-meteo.com/v1/search?name=Santiago
```

### Weather API

Obtiene datos meteorológicos usando coordenadas.

Ejemplo:

```
https://api.open-meteo.com/v1/forecast?latitude=-33.45&longitude=-70.66&current_weather=true
```

Documentación oficial:

https://open-meteo.com/

---

# 📁 Estructura del Proyecto

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
                    └── WeatherService.java
```

* **App.java** → interacción con el usuario
* **WeatherService.java** → lógica de consulta a la API

---

# 🔮 Mejoras Futuras

Posibles mejoras para el proyecto:

* Obtener ubicación automática del usuario
* Agregar pronóstico de varios días
* Crear una interfaz gráfica
* Guardar historial de consultas
* Implementar pruebas unitarias con JUnit
* Mejorar el manejo de errores y logs

---

# 📄 Licencia

Este proyecto fue desarrollado con fines educativos.
- Proyecto impulsado con IA para el curso: AI Training for Software Developer Graduates v2 - Spanish - 2026
