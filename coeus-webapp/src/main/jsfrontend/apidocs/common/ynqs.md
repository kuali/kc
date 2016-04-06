## Ynqs [/research-sys/api/v1/ynqs/]

### Get Ynqs by Key [GET /research-sys/api/v1/ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Ynqs [GET /research-sys/api/v1/ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Ynqs with Filtering [GET /research-sys/api/v1/ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + questionId
            + dateRequiredFor
            + description
            + effectiveDate
            + explanationRequiredFor
            + groupName
            + noOfAnswers
            + questionType
            + status
            + sortId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Ynqs [GET /research-sys/api/v1/ynqs/]
	 
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
		
### Get Blueprint API specification for Ynqs [GET /research-sys/api/v1/ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Ynqs.md"
            transfer-encoding:chunked


### Update Ynqs [PUT /research-sys/api/v1/ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Ynqs [PUT /research-sys/api/v1/ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Ynqs [POST /research-sys/api/v1/ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Ynqs [POST /research-sys/api/v1/ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Ynqs by Key [DELETE /research-sys/api/v1/ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynqs [DELETE /research-sys/api/v1/ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Ynqs with Matching [DELETE /research-sys/api/v1/ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + questionId
            + dateRequiredFor
            + description
            + effectiveDate
            + explanationRequiredFor
            + groupName
            + noOfAnswers
            + questionType
            + status
            + sortId


+ Response 204
