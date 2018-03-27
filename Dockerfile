FROM maven as build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN  mvn clean package

FROM tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=build /app/target/EstateAgent.war /usr/local/tomcat/webapps/ROOT.war
