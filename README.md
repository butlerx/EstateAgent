# EstateAgent

Estate agent system

### Development

#### Setup

On first run do

```
docker-compose -f docker-compose.yml -f docker-compose.install.yml up
```

This only has to be run when the `package.json` is modified

#### Running

To compile in development run

```
docker-compose up --build
```

You can access the server on `localhost:8080`

### Build

To produce WAR file run

```
docker-compose up --build tomcat
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
