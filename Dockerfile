FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/elasticache-sqs-0.0.1-SNAPSHOT.jar /deployments/elasticache-sqs-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/deployments/elasticache-sqs-0.0.1-SNAPSHOT.jar"]