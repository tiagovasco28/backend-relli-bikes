# Utiliza una imagen base con Java 17
FROM adoptopenjdk:17-jre-hotspot

# Establece un directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación Spring Boot a la imagen
COPY target/products-0.0.1-SNAPSHOT.jar /app/products-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que la aplicación Spring Boot escucha (ajusta el número de puerto según tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "products-0.0.1-SNAPSHOT.jar"]