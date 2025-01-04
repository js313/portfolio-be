# Use official openjdk image as base image
FROM openjdk:23-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the local machine to the container
COPY target/portfolio-*.jar /app/app.jar

# Expose port 8080 for the application to run
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
