# Design Document

## System Requirements

* Tomcat8
* Maven

## Installation

```
 mvn clean package
```

Copy the outputted `./target/EstateAgent.war` file into the `webapps` directory
in your tomcat installation.

Restart tomcat

## Structure

### Objects

There are three objects the api excepts those are `Property`, `Bid` and
`LocalDataTime`.

A `LocalDataTime` is from the java library and is in the form
`2007-12-03T10:15:30`.

A `Bid` Class. The bid class is an object used to bid on a property and store
who has the highest bid and what amount that is. A bid can be seen in the form

| Name   | Type   | Description                                 |
| ------ | ------ | ------------------------------------------- |
| offer  | Int    | A integer of euro amount of the bid         |
| bidder | String | A String used to identify who the bidder is |

A `Property` Class. This class is an object used to store a Property in the
database. A property can be seen in the form

| Name     | Type          | Description                                                                       |
| -------- | ------------- | --------------------------------------------------------------------------------- |
| id       | int           | Unique id of property                                                             |
| type     | string        | What type of property is it. `apartment` or `house`                               |
| bedrooms | int           | Number of bedrooms in the property                                                |
| district | int           | The postcode representing where in Dublin they are located e.g. 12 represents D12 |
| start    | LocalDataTime | The date and time the property is created                                         |
| end      | LocalDataTime | The date that the auction closes on                                               |
| price    | int           | The asking price for the property in euro                                         |

## Route

API routes for rest calls are as follows

| URI path                   | HTTP method | Description                                                                                                                                                             |
| -------------------------- | ----------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| /api/property              | GET         | returns an array of `Property` in `application/json`                                                                                                                    |
| /api/property              | POST        | create new `Property` with `Property` object sent returns the id of the new `Property`                                                                                  |
| /api/property/{id}         | GET         | returns `Property` with given id in `application/json`                                                                                                                  |
| /api/property/{id}         | PUT         | updates `Property` with given id to match `Property` object sent                                                                                                        |
| /api/property/{id}         | DELETE      | delete `Property` with given id                                                                                                                                         |
| /api/property/{id}/bid     | GET         | returns `Bid` for `Property` with given id in `application/json`                                                                                                        |
| /api/property/{id}/bid     | POST        | excepts `Bid` for `Property` with given id returns http 409 with error message if bid is too low to be excepted & 201 if the id is excepted                             |
| /api/property/{id}/booking | GET         | returns an Array of unbooked times in the form of `2007-12-03T10:15:30` for `Property` with given id in `application/json`                                              |
| /api/property/{id}/booking | POST        | excepts times in the form of `2007-12-03T10:15:30` for `Property` with given id returns 201 if booking is excepted an 409 with error message as reason if booking fails |
