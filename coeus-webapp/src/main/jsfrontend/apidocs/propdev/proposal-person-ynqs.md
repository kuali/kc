## Proposal Person Ynqs [/research-sys/api/v1/proposal-person-ynqs/]

### Get Proposal Person Ynqs by Key [GET /research-sys/api/v1/proposal-person-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Ynqs [GET /research-sys/api/v1/proposal-person-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Person Ynqs with Filtering [GET /research-sys/api/v1/proposal-person-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + questionId
            + answer
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Person Ynqs [GET /research-sys/api/v1/proposal-person-ynqs/]
	 
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
		
### Get Blueprint API specification for Proposal Person Ynqs [GET /research-sys/api/v1/proposal-person-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Ynqs.md"
            transfer-encoding:chunked


### Update Proposal Person Ynqs [PUT /research-sys/api/v1/proposal-person-ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Ynqs [PUT /research-sys/api/v1/proposal-person-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Person Ynqs [POST /research-sys/api/v1/proposal-person-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Ynqs [POST /research-sys/api/v1/proposal-person-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Person Ynqs by Key [DELETE /research-sys/api/v1/proposal-person-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Ynqs [DELETE /research-sys/api/v1/proposal-person-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Person Ynqs with Matching [DELETE /research-sys/api/v1/proposal-person-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + questionId
            + answer


+ Response 204
