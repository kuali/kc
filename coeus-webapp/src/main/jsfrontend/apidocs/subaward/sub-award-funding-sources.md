## Sub Award Funding Sources [/subaward/api/v1/sub-award-funding-sources/]

### Get Sub Award Funding Sources by Key [GET /subaward/api/v1/sub-award-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Funding Sources [GET /subaward/api/v1/sub-award-funding-sources/]
	 
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

### Get All Sub Award Funding Sources with Filtering [GET /subaward/api/v1/sub-award-funding-sources/]
    
+ Parameters

    + subAwardFundingSourceId (optional) - Subaward Funding Source Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + subAwardCode (optional) - 
    + sequenceNumber (optional) - 
    + awardId (optional) - A unique institutionally assigned number of a previously funded application. Maximum length is 12.

            
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
			
### Get Schema for Sub Award Funding Sources [GET /subaward/api/v1/sub-award-funding-sources/]
	                                          
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
    
            {"columns":["subAwardFundingSourceId","subAwardId","subAwardCode","sequenceNumber","awardId"],"primaryKey":"subAwardFundingSourceId"}
		
### Get Blueprint API specification for Sub Award Funding Sources [GET /subaward/api/v1/sub-award-funding-sources/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Funding Sources.md"
            transfer-encoding:chunked


### Update Sub Award Funding Sources [PUT /subaward/api/v1/sub-award-funding-sources/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Funding Sources [PUT /subaward/api/v1/sub-award-funding-sources/]

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

### Insert Sub Award Funding Sources [POST /subaward/api/v1/sub-award-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardFundingSourceId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Funding Sources [POST /subaward/api/v1/sub-award-funding-sources/]

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
            
### Delete Sub Award Funding Sources by Key [DELETE /subaward/api/v1/sub-award-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Funding Sources [DELETE /subaward/api/v1/sub-award-funding-sources/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Funding Sources with Matching [DELETE /subaward/api/v1/sub-award-funding-sources/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardFundingSourceId (optional) - Subaward Funding Source Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + subAwardCode (optional) - 
    + sequenceNumber (optional) - 
    + awardId (optional) - A unique institutionally assigned number of a previously funded application. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
