FROM eclipse-temurin:17-jre-alpine
COPY target/job-offers.jar /job-offers.jar
ENTRYPOINT ["java","-jar","/job-offers.jar"]