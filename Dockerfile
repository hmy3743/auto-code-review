FROM openjdk:13
RUN mkdir /app
COPY out/artifacts/auto_code_review_main_jar/auto-code-review.main.jar /app
WORKDIR /app
CMD ["java", "-jar", "auto-code-review.main.jar"]