# EstateAgent

Estate agent system

### Development

#### Running

To compile in development run

```
docker-compose up --build
```

You can access the server on `localhost:8080`

To run the cli run
`docker run --rm -it mvn exec:java -D"exec.mainClass"="com.dcu.client.App"`

### Build

To produce WAR file run

```
docker-compose up --build
docker cp estateagent_tomcat_1:/usr/local/tomcat/webapps/ROOT.war ROOT.war
```

### Features

* Server stores housing data
* Client can GET housing data
* Client can POST property listing for sale
* Client can POST bid on property
* Server must update list of bids
* Client can filter by price range
* Client can GET view times of a property
* Client can POST booking for property viewing
