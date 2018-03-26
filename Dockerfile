FROM maven as build
COPY . /app
WORKDIR /app
RUN  mvn clean package

FROM tomcat
COPY --from=build /app/target/EstateAgent.war /usr/local/tomcat/webapps
