FROM java 

COPY target/ems-brain-1.8.0.jar /

VOLUME ["/log", "/conf"] 

EXPOSE 9080

ENTRYPOINT ["java", "-jar", "ems-brain-1.8.0.jar", "--spring.config.location=/conf/application.properties"]
