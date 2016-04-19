## Narrative User Rights [/propdev/api/v1/narrative-user-rights/]

### Get Narrative User Rights by Key [GET /propdev/api/v1/narrative-user-rights/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}

### Get All Narrative User Rights [GET /propdev/api/v1/narrative-user-rights/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Narrative User Rights with Filtering [GET /propdev/api/v1/narrative-user-rights/]
    
+ Parameters

    + moduleNumber (optional) - Module Number. Maximum length is 4.
    + proposalNumber (optional) - Proposal Number. Maximum length is 12.
    + userId (optional) - User Id. Maximum length is 40.
    + accessType (optional) - Access Type. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Narrative User Rights [GET /propdev/api/v1/narrative-user-rights/]
	                                          
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
    
            {"columns":["moduleNumber","proposalNumber","userId","accessType"],"primaryKey":"moduleNumber:proposalNumber:userId"}
		
### Get Blueprint API specification for Narrative User Rights [GET /propdev/api/v1/narrative-user-rights/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narrative User Rights.md"
            transfer-encoding:chunked


### Update Narrative User Rights [PUT /propdev/api/v1/narrative-user-rights/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narrative User Rights [PUT /propdev/api/v1/narrative-user-rights/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Narrative User Rights [POST /propdev/api/v1/narrative-user-rights/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narrative User Rights [POST /propdev/api/v1/narrative-user-rights/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Narrative User Rights by Key [DELETE /propdev/api/v1/narrative-user-rights/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative User Rights [DELETE /propdev/api/v1/narrative-user-rights/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative User Rights with Matching [DELETE /propdev/api/v1/narrative-user-rights/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + moduleNumber (optional) - Module Number. Maximum length is 4.
    + proposalNumber (optional) - Proposal Number. Maximum length is 12.
    + userId (optional) - User Id. Maximum length is 40.
    + accessType (optional) - Access Type. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
