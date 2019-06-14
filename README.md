# showkokhon-scraper-api
[![Build Status](https://travis-ci.com/ShawonAshraf/showkokhon-scraper-api.svg?branch=master)](https://travis-ci.com/ShawonAshraf/showkokhon-scraper-api)

Scraps movie schedules from Star Cineplex and Blockbuster Cinemas websites.

## Running locally
- Clone the repo.
- `cd` into the dir and run : 
```bash
mvn clean package
```
- Run the war :
```bash
java -jar target/showkokhon-scraper-0.0.1-SNAPSHOT.war
```

- The server will be up on port `8080`. Use the client of your choice to make `HTTP` requests.

## API

### GET /scraper/v1/
Shows basic info.

```json
{
    "STATUS_CODE": 200,
    "DATA": null,
    "MSG": "Green All across the board!",
    "SENT_AT": "Sat Jun 15 00:36:16 BDT 2019"
}
```

### GET /scraper/v1/schedule/all
Runs the scraper and returns movie schedules from both websites. In case the scraper fails, the server will return a `null` value as reponse.

Example `null` response:
```json
{
    "STATUS_CODE": 200,
    "SENT_AT": "Sat Jun 15 00:18:02 BDT 2019",
    "movies": null
}
```

Example response:
```json
{
    "STATUS_CODE": 200,
    "SENT_AT": "Sat Jun 15 00:34:15 BDT 2019",
    "movies": [
        {
            "name": "Aabar Boshonto(2D)",
            "schedule": {
                "Wednesday, June 19, 2019": {
                    "Bashundhara Shopping Mall, Panthapath": {
                        "showTimes": [
                            "01:40 PM"
                        ]
                    }
                },
                "Monday, June 17, 2019": {
                    "Bashundhara Shopping Mall, Panthapath": {
                        "showTimes": [
                            "01:40 PM"
                        ]
                    }
                },
                .......
}
```

### GET /scraper/v1/schedule/
_Parameters:_
- `cinemaId` : 0 for Star Cineplex and 1 for Blockbuster Cinemas


### GET /scraper/v1/schedule/
_Parameters_
- `cinemaId`
- `location` : for multiple branches.

## LICENSE
MIT