## Context Valid Agendas [/research-sys/api/v1/context-valid-agendas/]

### Get Context Valid Agendas by Key [GET /research-sys/api/v1/context-valid-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}

### Get All Context Valid Agendas [GET /research-sys/api/v1/context-valid-agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Context Valid Agendas with Filtering [GET /research-sys/api/v1/context-valid-agendas/]
    
+ Parameters

    + id (optional) - 
    + contextId (optional) - 
    + agendaTypeId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Context Valid Agendas [GET /research-sys/api/v1/context-valid-agendas/]
	                                          
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
    
            {"columns":["id","contextId","agendaTypeId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Context Valid Agendas [GET /research-sys/api/v1/context-valid-agendas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Context Valid Agendas.md"
            transfer-encoding:chunked


### Update Context Valid Agendas [PUT /research-sys/api/v1/context-valid-agendas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Context Valid Agendas [PUT /research-sys/api/v1/context-valid-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Context Valid Agendas [POST /research-sys/api/v1/context-valid-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Context Valid Agendas [POST /research-sys/api/v1/context-valid-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contextId": "(val)","agendaTypeId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Context Valid Agendas by Key [DELETE /research-sys/api/v1/context-valid-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Agendas [DELETE /research-sys/api/v1/context-valid-agendas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Context Valid Agendas with Matching [DELETE /research-sys/api/v1/context-valid-agendas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + contextId (optional) - 
    + agendaTypeId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
