# --- Build stage (uses Maven) ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -U -DskipTests

# --- Run stage (only JRE) ---
FROM eclipse-temurin:17-jre as runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]