# EstateAgent
Estate agent system

Concurrency Assignment 2
Using Java-WS or Java-RS implement an estate agent system

### Build
```
mvn clean package
```

### Deploy
```
cp target/EstateAgent.war ~tomcat8/webapps
systemctl restart tomcat8
```

### Requirements

* Server stores housing data
* Client can GET housing data
* Client can POST property listing for sale
* Client can POST bid on property
* Server must update list of bids

### Extra Requirements

* Client can GET view times of a property
* Client can POST booking for property viewing
