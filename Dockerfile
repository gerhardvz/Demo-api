#syntac=docker/dockerfile:1
FROM ubuntu:latest

RUN apt-get update
RUN    apt-get install -y openjdk-16-jre
RUN    apt-get install -y ant
RUN    apt-get clean;

#RUN mkdir -p /dreamiot
#RUN addgroup -S zovra && adduser -S zovra -G zovra
#RUN chown zovra:zovra /dreamiot
#USER zovra:zovra

RUN mkdir -p /dreamiot/api
WORKDIR /dreamiot/api/

ARG JAR_FILE=target/automation-api-0.0.1.jar

COPY ${JAR_FILE} /dreamiot/api/
EXPOSE 8080
RUN pwd
RUN ls -la

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","automation-api-0.0.1.jar"]

