# Use Java 21 (matches your eclipse-temurin)
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy everything to container
COPY . .

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Expose port (Render uses 8080 by default)
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/user-management-system-0.0.1-SNAPSHOT.jar"]
