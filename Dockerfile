FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/user_management_docker.jar user_management_docker.jar
ENTRYPOINT ["java", "-jar", "/user_management_docker.jar"]