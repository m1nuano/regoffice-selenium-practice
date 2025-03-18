FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

CMD ["mvn", "clean", "test", "-DsuiteXmlFile=src/test/resources/regoffice"]
