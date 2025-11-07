FROM eclipse-temurin:21-jdk
LABEL authors="sametozalp"
EXPOSE 8080
COPY target/Velora-Sports-Backend-0.0.1-SNAPSHOT.jar Velora-Sports-Backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Velora-Sports-Backend-0.0.1-SNAPSHOT.jar"]