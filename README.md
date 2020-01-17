# showkokhon-scraper-api
[![Build Status](https://travis-ci.com/ShawonAshraf/showkokhon-scraper-api.svg?branch=master)](https://travis-ci.com/ShawonAshraf/showkokhon-scraper-api) [![CodeFactor](https://www.codefactor.io/repository/github/shawonashraf/showkokhon-scraper-api/badge)](https://www.codefactor.io/repository/github/shawonashraf/showkokhon-scraper-api) [![Build Status](https://dev.azure.com/shawonAshraf/showkokhon-scraper/_apis/build/status/ShawonAshraf.showkokhon-scraper-api?branchName=master)](https://dev.azure.com/shawonAshraf/showkokhon-scraper/_build/latest?definitionId=2&branchName=master)

Scraps movie schedules from Star Cineplex and Blockbuster Cinemas websites.

## mvn Package

From the command line
```bash
maven install com.showkokhon.scraper.showkokhon-scraper
```

Inside the `pom.xml` file of your project
```xml
<dependency>
	<groupId>com.showkokhon.scraper</groupId>
	<artifactId>showkokhon-scraper</artifactId>
	<version>1.0.0.RELEASE</version>
</dependency>
```

## Running locally
- Clone the repo.
- `cd` into the dir and run : 
```bash
mvn clean package
```
- Run the war :
```bash
java -jar target/showkokhon-scraper-2.0.0.RELEASE.war
```

- The server will be up on port `8080`. Use the client of your choice to make `HTTP` requests.

## API

### GET /

```json
{
    "STATUS_CODE": 200,
    "DATA": null,
    "MSG": "Showkokhon-Scraper-API",
    "SENT_AT": "2019-09-26T12:31:28.881Z"
}
```

### GET /scraper/v1/
Shows basic info.

```json
{
    "STATUS_CODE": 200,
    "DATA": null,
    "MSG": "Green All across the board!",
    "SENT_AT": "2019-08-01T21:01:31.786Z"
}
```

### GET /scraper/v1/schedule/all
Runs the scraper and returns movie schedules from both websites. In case the scraper fails, the server will return a `null` value as reponse.

__Example `null` response:__

```json
{
    "STATUS_CODE": 200,
    "SENT_AT": "2019-08-01T21:01:31.786Z",
    "movies": null
}
```

__Example response:__
_Partial response shown_

```json
{
    "STATUS_CODE": 200,
    "SENT_AT": "2019-08-01T21:01:31.786Z",
    "movies": [
        {
            "name": "Aabar Boshonto(2D)",
            "schedule": [
                {
                    "date": "Sunday, June 16, 2019",
                    "playingAt": [
                        {
                            "name": "Bashundhara Shopping Mall, Panthapath",
                            "showTimes": {
                                "showTimes": [
                                    "01:40 PM"
                                ]
                            }
                        }
                    ]
                },
                .......
}
```

### GET /scraper/v1/schedule/
_Parameters:_
- `cinemaId` : 0 for Star Cineplex and 1 for Blockbuster Cinemas

__Example request :__ 

`http://localhost:8080/scraper/v1/schedule/?cinemaId=0`

### GET /scraper/v1/schedule/
_Parameters_
- `cinemaId`
- `location` : for multiple branches.


__Example request :__ 

`http://localhost:8080/scraper/v1/schedule/individual/?cinemaId=0&location=bcity`

## LICENSE
MIT
