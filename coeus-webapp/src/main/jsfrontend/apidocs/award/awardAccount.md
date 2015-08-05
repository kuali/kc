##  GET /kc-dev/kc-award/v1/accounts?startIndex=2&size=5

Get all accounts that are available for use

+ Parameters
  	+ startIndex: `2` (number, optional) - Starting index of the results to be returned. If not included, all available accounts are returned.
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

            173
            {"totalFound":5,"count":5,"accounts":[{"id":3,"accountNumber":"876777","awardId":2697,"available":true},{"id":4,"accountNumber":"3223333","awardId":2731,"available":true},{"id":5,"accountNumber":"123456","awardId":2731,"available":true},{"id":6,"accountNumber":"3847747","awardId":2731,"available":true},{"id":7,"accountNumber":"433333","awardId":2768,"available":true}]}
            0

## GET /kc-dev/kc-award/v1/accounts/878787

Get information on a particular account

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body



+ Response 200
    + Headers

            Content-Type:application/json;charset=UTF-8


    + Body

            6a
            {"totalFound":1,"count":1,"accounts":[{"id":1,"accountNumber":"878787","awardId":2524,"available":false}]}
            0

## GET /kc-dev/kc-award/v1/accounts/awards/2524

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

             2f6
             {"totalFound":1,"count":1,"accounts":[{"accountName":"NIH-McGregorGeoff","accountNumber":"878787","adminContactAddressCityName":null,"adminContactAddressStateCode":null,"adminContactAddressStreetAddress":null,"adminContactAddressZipCode":null,"cfdaNumber":null,"defaultAddressCityName":"Coeus","defaultAddressStateCode":"MA","defaultAddressStreetAddress":"1118 Kuali Drive","defaultAddressZipCode":"53421","effectiveDate":"2015-08-01","expenseGuidelineText":"000021-00001","expirationDate":"2015-08-31","higherEdFunctionCode":"IPR","incomeGuidelineText":"Cost reimbursement Established ACH mechanism for sponsor","indirectCostRate":"090","indirectCostTypeCode":"","offCampusIndicator":false,"principalId":"10000000001","purposeText":"test","unit":"000001"}]}
             0

## PUT /kc-dev/kc-award/v1/accounts/878787

Set an account as available or unavailable

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body

            {"isAvailable":false}

+ Response 200
    + Headers

            Content-Length:0

    + Body





