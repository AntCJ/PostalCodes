# Postal Codes Application

This application calculates distance between 2 postal codes using Haversine formula, and updates the coordinates of a postal code, using Spring Boot back-end with REST APIs.

## Prerequisite

- Java 17, Maven
- cURL or REST client to test the APIs (Eg. Postman)

## Deploying Spring Boot

1. Build Maven project in root directory:  
`mvn clean install`
2. Run the built jar file:  
`java -jar target/postal-codes-0.0.1-SNAPSHOT.jar`  
(It might take 10-20 seconds until the application is fully deployed)

## Testing APIs
### Calculate distance API:
HTTP method: POST

Request URL:  
http://localhost:8080/api/locations/distance

Authorization header:  
Authorization: Basic dXNlcjpwYXNzd29yZA==

Request body:  
```
{
    "postcode_1": "WN8 7HH",
    "postcode_2": "SN9 5DD"
}
```

Example cURL command:
```
curl --location 'http://localhost:8080/api/locations/distance' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data '{
    "postcode_1": "WN8 7HH",
    "postcode_2": "SN9 5DD"
}'
```

### Update location API:
HTTP method: PUT

Request URL:  
http://localhost:8080/api/locations

Authorization header:  
Authorization: Basic dXNlcjpwYXNzd29yZA==

Request body:  
```
{
    "location": {
        "postcode": "IM4 7JL",
        "latitude": "49.0972900390625",
        "longitude": "-3.4639829993247986"
    }
}
```

Example cURL command:  
```
curl --location --request PUT 'http://localhost:8080/api/locations' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data '{
    "location": {
        "postcode": "IM4 7JL",
        "latitude": "49.0972900390625",
        "longitude": "-3.4639829993247986"
    }
}'
```