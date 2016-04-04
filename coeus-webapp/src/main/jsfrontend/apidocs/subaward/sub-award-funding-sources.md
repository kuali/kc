## Sub Award Funding Sources [/research-sys/api/v1/sub-award-funding-sources/]

### Get Sub Award Funding Sources by Key [GET /research-sys/api/v1/sub-award-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Funding Sources [GET /research-sys/api/v1/sub-award-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Funding Sources with Filtering [GET /research-sys/api/v1/sub-award-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardFundingSourceId
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + awardId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Funding Sources [GET /research-sys/api/v1/sub-award-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Sub Award Funding Sources [GET /research-sys/api/v1/sub-award-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Funding Sources.md"
            transfer-encoding:chunked


### Update Sub Award Funding Sources [PUT /research-sys/api/v1/sub-award-funding-sources/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Funding Sources [PUT /research-sys/api/v1/sub-award-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Funding Sources [POST /research-sys/api/v1/sub-award-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Funding Sources [POST /research-sys/api/v1/sub-award-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Funding Sources by Key [DELETE /research-sys/api/v1/sub-award-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Funding Sources [DELETE /research-sys/api/v1/sub-award-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Funding Sources with Matching [DELETE /research-sys/api/v1/sub-award-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardFundingSourceId
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + awardId


+ Response 204
