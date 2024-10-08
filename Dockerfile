﻿FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/luisaoproject-0.0.1.jar luisaoproject-0.0.1.jar
EXPOSE 8080

CMD ["java", "-jar", "luisaoproject-0.0.1.jar", "--spring.profiles.active=prod"]