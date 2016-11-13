# JavaChallenge

## Prerequisites
* JDK 8
* Maven

## How to compile and start
1. Clone the repository
2. Browse the _javachallenge_ folder and execute `mvn clean install`
3. Enter _output_ folder and run the command `java -jar rest-1.0-SNAPSHOT.jar`. The server is now listening for requests on port **8080**

## Instructions of use
### Storing a record
In order to insert a purchase record in the database, send a **POST** request to `http://localhost:8080/purchases/insert` with *application/json* content-type and the body as the following example shows:
```json
{
  "productType": "Some type",
  "expires": [
    2016,   year
    11,     month
    22,     day
    12,     hours
    0       minutes
  ],
  "purchaseDetails": {
    "id": 0,
    "description": "Some description",
    "quantity": 2,
    "value": 21.5
  }
}
```
The **id** field is left out on purpose, since this will be attributed by the database.

### Updating a record
Updating a record is similar to storing, but in this case the existing **id** is present in the request. Send a **POST** request to `http://localhost:8080/purchases/update` with *application/json* content-type and the body as the following example shows:
```json
{
  "id": 0,
  "productType": "Some type",
  "expires": [
    2016,
    11,
    22,
    12,
    0
  ],
  "purchaseDetails": {
    "id": 0,
    "description": "Some description",
    "quantity": 4,
    "value": 44.7
  }
}
```

### Retrieving a record
In order to retrieve a specific record using its **id**, send a **GET** request to `http://localhost:8080/purchases/<id>`, for instance `http://localhost:8080/purchases/2` . The API will return the Purchase details in JSON format.

### Deleting a record
In order to retrieve a specific record using its **id**, send a **DELETE** request to `http://localhost:8080/purchases/delete/<id>`, for instance `http://localhost:8080/purchases/delete/1` .

### Fetching purchase details related to valid company purchases
In order to fetch the purchase details of all valid purchases, send a **GET** request to `http://localhost:8080/purchases` . The API will return a list of all valid purchase details, in JSON format. Example:
```json
[
  {
    "id": 0,
    "productType": "Type 1",
    "expires": [
      2016,
      11,
      22,
      12,
      0
    ],
    "purchaseDetails": {
      "id": 0,
      "description": "Description 1",
      "quantity": 781,
      "value": 521.45
    }
  },
  {
    "id": 1,
    "productType": "Type 2",
    "expires": [
      2016,
      11,
      13,
      15,
      11
    ],
    "purchaseDetails": {
      "id": 0,
      "description": "Description",
      "quantity": 357,
      "value": 127.80
    }
  }
]
```
