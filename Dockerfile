# define base docker image
FROM openjdk:11
LABEL maintainer="company"
ADD target/company-0.0.1-SNAPSHOT.jar company-docker-image.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","company-docker-image.jar"]