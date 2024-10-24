FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y openjdk-17-jdk
WORKDIR /dssd-api
COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/BackendApi-0.0.1-SNAPSHOT.jar"]
