## Context Valid Terms [/research-sys/api/v1/context-valid-terms/]

### Get Context Valid Terms by Key [GET /research-sys/api/v1/context-valid-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}

### Get All Context Valid Terms [GET /research-sys/api/v1/context-valid-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Context Valid Terms with Filtering [GET /research-sys/api/v1/context-valid-terms/]
    
+ Parameters

    + id (optional) - 
    + contextId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Context Valid Terms [GET /research-sys/api/v1/context-valid-terms/]
	                                          
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
    
            {"columns":["id","contextId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Context Valid Terms [GET /research-sys/api/v1/context-valid-terms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Context Valid Terms.md"
            transfer-encoding:chunked


### Update Context Valid Terms [PUT /research-sys/api/v1/context-valid-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Context Valid Terms [PUT /research-sys/api/v1/context-valid-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Context Valid Terms [POST /research-sys/api/v1/context-valid-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Context Valid Terms [POST /research-sys/api/v1/context-valid-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Context Valid Terms by Key [DELETE /research-sys/api/v1/context-valid-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Terms [DELETE /research-sys/api/v1/context-valid-terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Terms with Matching [DELETE /research-sys/api/v1/context-valid-terms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + contextId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
