FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u232-b09
EXPOSE 5060
COPY ./target/java-maven-app-* /usr/app/
WORKDIR /usr/app

CMD java -jar java-maven-app-*.jar