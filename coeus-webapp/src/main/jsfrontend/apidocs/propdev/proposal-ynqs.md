## Proposal Ynqs [/research-sys/api/v1/proposal-ynqs/]

### Get Proposal Ynqs by Key [GET /research-sys/api/v1/proposal-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Ynqs [GET /research-sys/api/v1/proposal-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Ynqs with Filtering [GET /research-sys/api/v1/proposal-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalNumber
            + questionId
            + answer
            + explanation
            + reviewDate
            + sortId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Ynqs [GET /research-sys/api/v1/proposal-ynqs/]
	 
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
		
### Get Blueprint API specification for Proposal Ynqs [GET /research-sys/api/v1/proposal-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Ynqs.md"
            transfer-encoding:chunked


### Update Proposal Ynqs [PUT /research-sys/api/v1/proposal-ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Ynqs [PUT /research-sys/api/v1/proposal-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Ynqs [POST /research-sys/api/v1/proposal-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Ynqs [POST /research-sys/api/v1/proposal-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Ynqs by Key [DELETE /research-sys/api/v1/proposal-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Ynqs [DELETE /research-sys/api/v1/proposal-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Ynqs with Matching [DELETE /research-sys/api/v1/proposal-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalNumber
            + questionId
            + answer
            + explanation
            + reviewDate
            + sortId


+ Response 204
