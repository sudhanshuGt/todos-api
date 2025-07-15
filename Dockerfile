# ---------- 1. Build Stage ----------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace

COPY mvnw ./
COPY .mvn/ .mvn/
COPY pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw -B -ntp dependency:go-offline

COPY src/ src/
RUN ./mvnw -B -ntp clean package -DskipTests

# ---------- 2. Run Stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8080

COPY --from=build /workspace/target/todos-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
