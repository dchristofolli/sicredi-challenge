FROM openjdk:11.0.13
WORKDIR /app
COPY . .
RUN ./gradlew clean build
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/sicredi-challenge-0.0.1-SNAPSHOT.jar"]
