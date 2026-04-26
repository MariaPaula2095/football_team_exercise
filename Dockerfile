FROM maven:3.9-amazoncorretto-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar api-v1.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","api-v1.jar"]