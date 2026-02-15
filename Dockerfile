# Use Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the Spring Boot app
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/user-management-system-0.0.1-SNAPSHOT.jar"]

