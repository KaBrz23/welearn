FROM tomcat:10

WORKDIR /usr/local/tomcat/webapps

COPY target/welearn-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/welearn-0.0.1-SNAPSHOT.war

EXPOSE 8080

ENTRYPOINT ["catalina.sh", "run"]