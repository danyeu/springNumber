FROM eclipse-temurin:21

COPY target/number.jar app.jar

EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "app.jar" ]
