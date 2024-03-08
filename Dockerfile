FROM openjdk:17
ADD target/springboot-reactiveapi.jar springboot-reactiveapi.jar
ENTRYPOINT ["java","-jar","/springboot-reactiveapi-docker.jar"]