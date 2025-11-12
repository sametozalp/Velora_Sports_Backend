#FROM eclipse-temurin:21-jdk
#LABEL authors="sametozalp"
#EXPOSE 8080
#COPY target/Velora-Sports-Backend-0.0.1-SNAPSHOT.jar Velora-Sports-Backend-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "Velora-Sports-Backend-0.0.1-SNAPSHOT.jar"]

# Build aşaması
FROM maven:3.9.2-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run aşaması
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/Velora-Sports-Backend-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Velora-Sports-Backend-0.0.1-SNAPSHOT.jar"]
