## Award Accounts [/award/api/v1/accounts/]  

### List All Accounts [GET /award/api/v1/accounts/{?startIndex=0&size=5}]

Get all accounts that are available for use

+ Parameters
  	+ startIndex: `0` (number, optional) - Starting index of the results to be returned. If not included, all available accounts are returned.
  	+ size: `5` (number, optional) - Size of the results to be returned. If not included, all available accounts are returned.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

    + Body



+ Response 200
    + Headers

            Content-Type: application/json;

    + Body

            {"totalFound": 1, "count": 1, "accounts": [ {"id": 1, "accountNumber": "55555", "createdByAwardId": 2609, "status": "AVAILABLE", "budgeted": 0, "pending": 0, "income": 0, "expense": 0, "available": 0 }]}

### Get Account [GET /award/api/v1/accounts/55555]

Get information on a particular account

+ Parameters
  	+ showAwards: `true` (boolean, optional) - Flag to indicate if award ids linked to account should be returned.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

    + Body



+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8


    + Body

            {"totalFound":1,"count":1,"accounts":[{"id":9,"accountNumber":"55555","createdByAwardId":2742,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":999.99,"expense":8.80,"available":5.50}],"awards":[2742,2778]}

### Update Account [PUT /award/api/v1/accounts/878787]

Set various fields on an account. Fields not provided will not be updated.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

    + Body

            {"available":5.5, "budgeted":6.688, "pending":7.7, "expense":8.8, "income":999.99, "status":"CLOSED"}


    + Attributes
      + available (number) - available amount (max integral digits = 12, max fractional digits = 2)
      + budgeted (number) - budgeted amount (max integral digits = 12, max fractional digits = 2)
      + pending (number) - pending amount (max integral digits = 12, max fractional digits = 2)
      + expense (number) - expense amount (max integral digits = 12, max fractional digits = 2)
      + income (number) - income amount (max integral digits = 12, max fractional digits = 2)
      + status (string) - status (min size = 1, max size = 15], pattern [a-zA-Z]+)

+ Response 200
    + Headers

            Content-Length:0

    + Body





