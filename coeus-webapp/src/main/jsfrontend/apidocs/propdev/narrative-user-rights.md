## Narrative User Rights [/research-sys/api/v1/narrative-user-rights/]

### Get Narrative User Rights by Key [GET /research-sys/api/v1/narrative-user-rights/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}

### Get All Narrative User Rights [GET /research-sys/api/v1/narrative-user-rights/]
	 
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

### Get All Narrative User Rights with Filtering [GET /research-sys/api/v1/narrative-user-rights/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + moduleNumber
            + proposalNumber
            + userId
            + accessType
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Narrative User Rights [GET /research-sys/api/v1/narrative-user-rights/]
	 
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
		
### Get Blueprint API specification for Narrative User Rights [GET /research-sys/api/v1/narrative-user-rights/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narrative User Rights.md"
            transfer-encoding:chunked


### Update Narrative User Rights [PUT /research-sys/api/v1/narrative-user-rights/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narrative User Rights [PUT /research-sys/api/v1/narrative-user-rights/]

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

### Insert Narrative User Rights [POST /research-sys/api/v1/narrative-user-rights/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"moduleNumber": "(val)","proposalNumber": "(val)","userId": "(val)","accessType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narrative User Rights [POST /research-sys/api/v1/narrative-user-rights/]

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
            
### Delete Narrative User Rights by Key [DELETE /research-sys/api/v1/narrative-user-rights/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative User Rights [DELETE /research-sys/api/v1/narrative-user-rights/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Narrative User Rights with Matching [DELETE /research-sys/api/v1/narrative-user-rights/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + moduleNumber
            + proposalNumber
            + userId
            + accessType


+ Response 204
