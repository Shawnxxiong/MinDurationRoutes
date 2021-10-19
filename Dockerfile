FROM maven:3.8.3-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM adoptopenjdk/openjdk11
COPY --from=build /home/app/target/interview-1.0-SNAPSHOT.jar /usr/local/lib/interview.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/interview.jar"]