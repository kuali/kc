##  GET /kc-dev/kc-award/v1/accounts?startIndex=0&size=5

Get all accounts that are available for use

+ Parameters
  	+ startIndex: `0` (number, optional) - Starting index of the results to be returned. If not included, all available accounts are returned.
  	+ size: `5` (number, optional) - Size of the results to be returned. If not included, all available accounts are returned.

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body



+ Response 200
    + Headers

            Content-Type:application/json;

    + Body

             {"totalFound":2,"count":2,"accounts":[{"id":8,"accountNumber":"123456455","createdByAwardId":2670,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":9.90,"expense":8.80,"available":5.50},{"id":9,"accountNumber":"55555","createdByAwardId":2742,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":999.99,"expense":8.80,"available":5.50}],"awards":[]}

## GET /kc-dev/kc-award/v1/accounts/55555?showAwards=true

Get information on a particular account

+ Parameters
  	+ showAwards: `true` (boolean, optional) - Flag to indicate if award ids linked to account should be returned.

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body



+ Response 200
    + Headers

            Content-Type:application/json;charset=UTF-8


    + Body

            {"totalFound":1,"count":1,"accounts":[{"id":9,"accountNumber":"55555","createdByAwardId":2742,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":999.99,"expense":8.80,"available":5.50}],"awards":[2742,2778]}

## GET /kc-dev/kc-award/v1/accounts/awards/2742

Get award details required to create an account using award id

+ Request
     + Headers

             Authorization:Basic
             Content-Type:application/json

     + Body



+ Response 200
     + Headers

             Content-Type:application/json;charset=UTF-8

     + Body

             {"totalFound":1,"count":1,"accounts":[{"accountName":"NIH-McGregorGeoff","accountNumber":"55555","adminContactAddressCityName":null,"adminContactAddressStateCode":null,"adminContactAddressStreetAddress":null,"adminContactAddressZipCode":null,"cfdaNumber":null,"defaultAddressCityName":"Coeus","defaultAddressStateCode":"MA","defaultAddressStreetAddress":"1118 Kuali Drive","defaultAddressZipCode":"53421","effectiveDate":"2015-08-01","expenseGuidelineText":"000022-00001","expirationDate":"2015-08-31","higherEdFunctionCode":"IPR","incomeGuidelineText":"Cost reimbursement Established ACH mechanism for sponsor","indirectCostRate":"","indirectCostTypeCode":"","offCampusIndicator":false,"principalId":"10000000001","purposeText":"test","unit":"000001"}]}

## PUT /kc-dev/kc-award/v1/accounts/878787

Set various fields on an account. Fields not provided will not be updated.

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

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





