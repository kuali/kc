## Context Valid Actions [/research-sys/api/v1/context-valid-actions/]

### Get Context Valid Actions by Key [GET /research-sys/api/v1/context-valid-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}

### Get All Context Valid Actions [GET /research-sys/api/v1/context-valid-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Context Valid Actions with Filtering [GET /research-sys/api/v1/context-valid-actions/]
    
+ Parameters

        + id
            + contextId
            + actionTypeId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Context Valid Actions [GET /research-sys/api/v1/context-valid-actions/]
	                                          
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
    
            {"columns":["id","contextId","actionTypeId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Context Valid Actions [GET /research-sys/api/v1/context-valid-actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Context Valid Actions.md"
            transfer-encoding:chunked


### Update Context Valid Actions [PUT /research-sys/api/v1/context-valid-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Context Valid Actions [PUT /research-sys/api/v1/context-valid-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Context Valid Actions [POST /research-sys/api/v1/context-valid-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Context Valid Actions [POST /research-sys/api/v1/context-valid-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","actionTypeId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Context Valid Actions by Key [DELETE /research-sys/api/v1/context-valid-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Actions [DELETE /research-sys/api/v1/context-valid-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Actions with Matching [DELETE /research-sys/api/v1/context-valid-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + contextId
            + actionTypeId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
