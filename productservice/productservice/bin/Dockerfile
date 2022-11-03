FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8083

COPY ./target/productservice-0.0.1-SNAPSHOT.jar productservice.jar

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar ./productservice.jar"]