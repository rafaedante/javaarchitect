FROM frolvlad/alpine-oraclejre8:slim
MAINTAINER rafel.dante@gmail.com
ADD myservicio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]