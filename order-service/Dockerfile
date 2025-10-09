FROM eclipse-temurin:21-jdk
WORKDIR /app

# Копіюємо вже зібрану локальну jar-ку
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]