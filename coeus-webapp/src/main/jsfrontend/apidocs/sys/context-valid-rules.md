## Context Valid Rules [/research-sys/api/v1/context-valid-rules/]

### Get Context Valid Rules by Key [GET /research-sys/api/v1/context-valid-rules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}

### Get All Context Valid Rules [GET /research-sys/api/v1/context-valid-rules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Context Valid Rules with Filtering [GET /research-sys/api/v1/context-valid-rules/]
    
+ Parameters

    + id (optional) - 
    + contextId (optional) - 
    + ruleTypeId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Context Valid Rules [GET /research-sys/api/v1/context-valid-rules/]
	                                          
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
    
            {"columns":["id","contextId","ruleTypeId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Context Valid Rules [GET /research-sys/api/v1/context-valid-rules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Context Valid Rules.md"
            transfer-encoding:chunked
### Update Context Valid Rules [PUT /research-sys/api/v1/context-valid-rules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Context Valid Rules [PUT /research-sys/api/v1/context-valid-rules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Context Valid Rules [POST /research-sys/api/v1/context-valid-rules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Context Valid Rules [POST /research-sys/api/v1/context-valid-rules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","ruleTypeId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Context Valid Rules by Key [DELETE /research-sys/api/v1/context-valid-rules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Rules [DELETE /research-sys/api/v1/context-valid-rules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Rules with Matching [DELETE /research-sys/api/v1/context-valid-rules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + contextId (optional) - 
    + ruleTypeId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
