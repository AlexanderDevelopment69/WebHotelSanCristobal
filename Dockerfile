# Usa la imagen oficial de OpenJDK como base
FROM openjdk:17

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/HotelSanCristobal-0.0.1-SNAPSHOT.jar /app/HotelSanCristobal-0.0.1-SNAPSHOT.jar

# Establece el directorio de trabajo
WORKDIR /app

# Expone el puerto en el que tu aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "tu-aplicacion.jar"]
