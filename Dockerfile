FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .


RUN mvn -DskipTests clean package \
	&& cp "$(find target -maxdepth 1 -type f -name '*.jar' ! -name '*original*' | head -n 1)" /app/app.jar

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/app.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

