## Proposal Person Ynqs [/propdev/api/v1/proposal-person-ynqs/]

### Get Proposal Person Ynqs by Key [GET /propdev/api/v1/proposal-person-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Ynqs [GET /propdev/api/v1/proposal-person-ynqs/]
	 
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

### Get All Proposal Person Ynqs with Filtering [GET /propdev/api/v1/proposal-person-ynqs/]
    
+ Parameters

    + questionId (optional) - Question Id. Maximum length is 4.
    + answer (optional) - Answer. Maximum length is 1.

            
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
			
### Get Schema for Proposal Person Ynqs [GET /propdev/api/v1/proposal-person-ynqs/]
	                                          
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
    
            {"columns":["questionId","answer"],"primaryKey":"proposalPerson:ynq"}
		
### Get Blueprint API specification for Proposal Person Ynqs [GET /propdev/api/v1/proposal-person-ynqs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Ynqs.md"
            transfer-encoding:chunked


### Update Proposal Person Ynqs [PUT /propdev/api/v1/proposal-person-ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Ynqs [PUT /propdev/api/v1/proposal-person-ynqs/]

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

### Insert Proposal Person Ynqs [POST /propdev/api/v1/proposal-person-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"questionId": "(val)","answer": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Ynqs [POST /propdev/api/v1/proposal-person-ynqs/]

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
            
### Delete Proposal Person Ynqs by Key [DELETE /propdev/api/v1/proposal-person-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Ynqs [DELETE /propdev/api/v1/proposal-person-ynqs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Ynqs with Matching [DELETE /propdev/api/v1/proposal-person-ynqs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + questionId (optional) - Question Id. Maximum length is 4.
    + answer (optional) - Answer. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
