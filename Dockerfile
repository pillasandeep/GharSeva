# Use JDK 21 instead of 17
FROM openjdk:21-jdk-slim

# Set working directory inside container
ENV APP_HOME=/app
WORKDIR $APP_HOME

# Copy the JAR file and rename it to app.jar
COPY target/Webapp-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "Webapp-0.0.1-SNAPSHOT.jar"]
