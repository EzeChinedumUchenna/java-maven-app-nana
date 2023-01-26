FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u232-b09
EXPOSE 8080
COPY ./target/java-maven-app-1.1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "java-maven-app-1.0-SNAPSHOT.jar"] 