# ---------- 1 · BUILD STAGE ---------------------------------------------------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace

# -- Copy wrapper & POM first for better caching
COPY mvnw* .mvn/ pom.xml /workspace/
RUN ./mvnw -B -ntp dependency:go-offline

# -- Copy source and build the Spring Boot fat‑JAR
COPY src /workspace/src
RUN ./mvnw -B -ntp clean package -DskipTests  \
    && echo "Built JARs:" && ls -l target/*.jar

# ---------- 2 · RUN STAGE -----------------------------------------------------
FROM eclipse-temurin:17-jre-slim
WORKDIR /app
EXPOSE 8080

# Copy the boot‑JAR from the build stage
COPY --from=build /workspace/target/todos-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
