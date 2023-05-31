FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=build/libs/pethome-1.0.0-SNAPSHOT.jar
ARG DB_HOST=5432
ARG DB_PORT=5432
ARG DB_USERNAME=postgres
ARG DB_PASSWORD=marsel55
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
