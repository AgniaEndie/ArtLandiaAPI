FROM bellsoft/liberica-openjdk-alpine:17
LABEL authors="Endie"

COPY build/libs/artlandia-*-SNAPSHOT.jar /server.jar
CMD ["java","-jar","/server.jar"]
