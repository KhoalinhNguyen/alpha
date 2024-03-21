# FROM openjdk:17
# ADD target/Alpha-0.0.1-SNAPSHOT.jar Alpha-0.0.1-SNAPSHOT.jar
# EXPOSE 8082
# ENTRYPOINT ["java", "-jar", "Alpha-0.0.1-SNAPSHOT.jar"]

#
# Build
#
FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM openjdk:17
COPY --from=build /home/app/target/alpha-0.0.1-SNAPSHOT.jar /usr/local/lib/alpha.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/alpha.jar"]