# Usa la imagen oficial de OpenJDK como base
FROM openjdk:17

# Crea un directorio de la aplicación en el contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY HotelSanCristobal-0.0.1-SNAPSHOT.jar /app/HotelSanCristobal-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que tu aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "HotelSanCristobal-0.0.1-SNAPSHOT.jar"]
