# Design Document

## System Requirements

* Tomcat8
* Maven

## Installation
```
 mvn clean package
```
Copy the outputted .war file into the webapps directory in your tomcat installation

restart tomcat

## Structure

Each function of the server is broken down into a separate REST call.

* Clients can make a GET request for property Data
* Clients can make a GET request for a single specific property
* Clients can make a POST request to add a new property to the list
* Clients can make a POST request to add a new bid to a property
