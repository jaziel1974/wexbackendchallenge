FROM eclipse-temurin:17.0.8.1_1-jdk-jammy
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install
ENTRYPOINT ["java","-jar","target/wexbackendchallenge-0.0.1-SNAPSHOT.jar" ]