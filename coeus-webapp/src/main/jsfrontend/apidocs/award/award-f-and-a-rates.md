## Award F And A Rates [/research-sys/api/v1/award-f-and-a-rates/]

### Get Award F And A Rates by Key [GET /research-sys/api/v1/award-f-and-a-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}

### Get All Award F And A Rates [GET /research-sys/api/v1/award-f-and-a-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award F And A Rates with Filtering [GET /research-sys/api/v1/award-f-and-a-rates/]
    
+ Parameters

        + awardFandaRateId
            + awardId
            + awardNumber
            + sequenceNumber
            + applicableFandaRate
            + fandaRateTypeCode
            + fiscalYear
            + onCampusFlag
            + underrecoveryOfIndirectCost
            + sourceAccount
            + destinationAccount
            + startDate
            + endDate

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award F And A Rates [GET /research-sys/api/v1/award-f-and-a-rates/]
	                                          
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
    
            {"columns":["awardFandaRateId","awardId","awardNumber","sequenceNumber","applicableFandaRate","fandaRateTypeCode","fiscalYear","onCampusFlag","underrecoveryOfIndirectCost","sourceAccount","destinationAccount","startDate","endDate"],"primaryKey":"awardFandaRateId"}
		
### Get Blueprint API specification for Award F And A Rates [GET /research-sys/api/v1/award-f-and-a-rates/]
	 
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


### Update Award F And A Rates [PUT /research-sys/api/v1/award-f-and-a-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award F And A Rates [PUT /research-sys/api/v1/award-f-and-a-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award F And A Rates [POST /research-sys/api/v1/award-f-and-a-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award F And A Rates [POST /research-sys/api/v1/award-f-and-a-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"awardFandaRateId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","applicableFandaRate": "(val)","fandaRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectCost": "(val)","sourceAccount": "(val)","destinationAccount": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award F And A Rates by Key [DELETE /research-sys/api/v1/award-f-and-a-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award F And A Rates [DELETE /research-sys/api/v1/award-f-and-a-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award F And A Rates with Matching [DELETE /research-sys/api/v1/award-f-and-a-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardFandaRateId
            + awardId
            + awardNumber
            + sequenceNumber
            + applicableFandaRate
            + fandaRateTypeCode
            + fiscalYear
            + onCampusFlag
            + underrecoveryOfIndirectCost
            + sourceAccount
            + destinationAccount
            + startDate
            + endDate

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
