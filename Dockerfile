FROM node:carbon as frontend
WORKDIR /webapp
COPY ./src/main/webapp/package.json ./src/main/webapp/yarn.lock ./
RUN yarn
COPY ./src/main/webapp .
RUN yarn build

FROM maven:3-jdk-9 as build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN rm -rf /app/src/main/webapp
COPY --from=frontend /webapp/dist /app/src/main/webapp
COPY --from=frontend /webapp/WEB-INF /app/src/main/webapp/WEB-INF
RUN mvn package

FROM tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/EstateAgent.war /usr/local/tomcat/webapps/ROOT.war
