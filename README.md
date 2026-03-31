# 🌤️ Clima App

Aplicación desarrollada en Java que permite consultar el clima actual de cualquier ciudad utilizando la API de Open-Meteo.

---

## 👩‍💻 Autora

**Camila Reyes**

---

## 📌 Descripción

Clima App es una aplicación de consola construida con Java y Maven que permite a los usuarios ingresar el nombre de una ciudad y obtener información meteorológica en tiempo real.

La aplicación utiliza la API de Open-Meteo para:

* Obtener coordenadas geográficas a partir del nombre de la ciudad
* Consultar el clima actual según la ubicación obtenida

---

## 🚀 Tecnologías utilizadas

* Java (JDK 17+)
* Maven
* API Open-Meteo
* Librería org.json

---

## ⚙️ Funcionalidades

* 🔍 Búsqueda de clima por nombre de ciudad
* 🌡️ Muestra la temperatura actual
* 💨 Muestra la velocidad del viento
* ☁️ Traducción del estado del clima (ej: despejado, lluvia, nieve)
* ❌ Manejo de error cuando la ciudad no existe

---

## 🧱 Estructura del proyecto

```
Clima
├── pom.xml
├── README.md
├── .gitignore
└── src
    └── main
        └── java
            └── com
                └── clima
                    └── App.java
```

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio:

```
git clone https://github.com/tu-usuario/clima-app.git
```

2. Entrar a la carpeta del proyecto:

```
cd clima-app
```

3. Compilar el proyecto:

```
mvn clean compile
```

4. Ejecutar la aplicación:

```
mvn exec:java -Dexec.mainClass="com.clima.App"
```

---

## 🧪 Ejemplo de uso

```
Ingresa una ciudad: Santiago

Clima en Santiago:
Temperatura: 22°C
Viento: 10 km/h
Condición: Despejado ☀️
```

---

## 🔮 Posibles mejoras futuras

* 📍 Obtener ubicación automática del usuario
* 🖥️ Interfaz gráfica (JavaFX)
* 💾 Guardar historial de búsquedas
* 🌐 Soporte para más idiomas
* 📊 Mostrar pronóstico extendido

---

## 📡 API utilizada

* Open-Meteo (https://open-meteo.com/)

---

## 📄 Licencia

Este proyecto es de uso educativo y libre para mejorar o modificar.

---
