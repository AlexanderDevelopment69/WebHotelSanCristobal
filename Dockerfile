# Usa la imagen oficial de Tomcat como base
FROM tomcat:latest

# Copia el archivo JAR de tu aplicación al directorio de despliegue de Tomcat
COPY target/HotelSanCristobal-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/

# Exponer el puerto en el que Tomcat escucha
EXPOSE 8080

# Comando para iniciar Tomcat cuando se ejecuta el contenedor
CMD ["catalina.sh", "run"]
