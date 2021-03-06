FROM openjdk:8

#RUN addgroup --system spring
#RUN adduser --system spring
#RUN adduser spring spring
#USER spring:spring

ARG A_FILE=spring-main/target/*.war
COPY ${A_FILE} CBM.war

EXPOSE 8080

ENV JAVA_OPTS="-Xmx256m"
ENTRYPOINT ["java", "-jar", "/CBM.war"]
