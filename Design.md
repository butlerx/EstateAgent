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
** Queries the database of properties and returns an array of all properties along with their information
* Clients can make a GET request for a single specific property
** Queries the database of properties for a property matching the specified id number and returns the information pertaining to that property
* Clients can make a POST request to add a new property to the list
** Adds a new property to the properties database
* Clients can make a POST request to add a new bid to a property
** Requires the bid to fall within acceptable parameters and the registeres the bid, ie: within the bidding window, higher than existing bid, higher than minimum price
