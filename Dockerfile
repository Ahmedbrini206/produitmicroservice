# Étape 1 : Utiliser une image Java 17 optimisée
FROM eclipse-temurin:17-jdk-alpine

# Étape 2 : Définir le répertoire de travail
WORKDIR /app

# Étape 3 : Copier le JAR compilé depuis Maven
COPY target/jpa-spring-boot-relations-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : Exposer le port utilisé par Spring Boot (ajuste si nécessaire)
EXPOSE 8083

# Étape 5 : Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
