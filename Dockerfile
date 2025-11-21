FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/DemoSupermercado-0.1.0.jar
COPY ${JAR_FILE} app_DemoSupermercado.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_DemoSupermercado.jar"]