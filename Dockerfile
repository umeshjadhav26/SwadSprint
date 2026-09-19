FROM tomcat:10.1-jdk21-temurin

WORKDIR /app

COPY src/main/java /app/src/main/java
COPY src/main/webapp /app/webapp

RUN mkdir -p /app/webapp/WEB-INF/classes

RUN javac -encoding UTF-8 \
    -cp "/usr/local/tomcat/lib/*:/app/webapp/WEB-INF/lib/*" \
    -d /app/webapp/WEB-INF/classes \
    $(find /app/src/main/java -name "*.java")

RUN rm -rf /usr/local/tomcat/webapps/ROOT/*

RUN cp -r /app/webapp/. /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080

CMD ["catalina.sh", "run"]