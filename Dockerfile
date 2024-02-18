FROM openjdk:21-jdk
COPY target/spring-atm1-spring-boot.jar spring-atm1.jar
ENTRYPOINT ["java", "-jar", "spring-atm1.jar"]