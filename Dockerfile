# Usa una imagen ligera de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Crea un directorio dentro del contenedor
WORKDIR /app

# Copia el archivo .jar compilado al contenedor
COPY target/*.jar app.jar

# Exponer el puerto dinámico asignado por Render
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "app.jar"]
