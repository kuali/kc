## Award F And A Rates [/award/api/v1/award-f-and-a-rates/]

### Get Award F And A Rates by Key [GET /award/api/v1/award-f-and-a-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardFandaRateId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}

### Get All Award F And A Rates [GET /award/api/v1/award-f-and-a-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFandaRateId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award F And A Rates with Filtering [GET /award/api/v1/award-f-and-a-rates/]
    
+ Parameters

    + awardFandaRateId (optional) - Award Idc Rate Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 8.
    + applicableFandaRate (optional) - Applicable Idc Rate. Maximum length is 22.
    + fandaRateTypeCode (optional) - F&A Rate Type Code. Maximum length is 22.
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + onCampusFlag (optional) - On CampusContractContract Flag. Maximum length is 1.
    + underrecoveryOfIndirectCost (optional) - Underrecovery Of Idc. Maximum length is 22.
    + sourceAccount (optional) - Source Account. Maximum length is 32.
    + destinationAccount (optional) - Destination Account. Maximum length is 32.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFandaRateId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award F And A Rates [GET /award/api/v1/award-f-and-a-rates/]
	                                          
+ Parameters

      + _schema (required) - will instruct the endpoint to return a schema data structure for the resource
      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columns":["awardFandaRateId","awardNumber","sequenceNumber","applicableFandaRate","fandaRateTypeCode","fiscalYear","onCampusFlag","underrecoveryOfIndirectCost","sourceAccount","destinationAccount","startDate","endDate"],"primaryKey":"awardFandaRateId"}
		
### Get Blueprint API specification for Award F And A Rates [GET /award/api/v1/award-f-and-a-rates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award F And A Rates.md"
            transfer-encoding:chunked
