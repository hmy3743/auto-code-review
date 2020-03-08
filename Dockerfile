FROM openjdk:13
RUN mkdir /app
COPY /build/libs/auto-code-review-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
CMD ["java", "-jar", "auto-code-review-0.0.1-SNAPSHOT.jar"]