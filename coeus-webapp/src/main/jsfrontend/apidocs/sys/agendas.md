## Agendas [/research-sys/api/v1/agendas/]

### Get Agendas by Key [GET /research-sys/api/v1/agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}

### Get All Agendas [GET /research-sys/api/v1/agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Agendas with Filtering [GET /research-sys/api/v1/agendas/]
    
+ Parameters

    + id (optional) - Agenda Id.
    + name (optional) - Agenda Name.
    + typeId (optional) - Agenda typeId.
    + contextId (optional) - Agenda contextId.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + firstItemId (optional) - First Item Id.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Agendas [GET /research-sys/api/v1/agendas/]
	                                          
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
    
            {"columns":["id","name","typeId","contextId","active","firstItemId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Agendas [GET /research-sys/api/v1/agendas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Agendas.md"
            transfer-encoding:chunked
### Update Agendas [PUT /research-sys/api/v1/agendas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Agendas [PUT /research-sys/api/v1/agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Agendas [POST /research-sys/api/v1/agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Agendas [POST /research-sys/api/v1/agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","typeId": "(val)","contextId": "(val)","active": "(val)","firstItemId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Agendas by Key [DELETE /research-sys/api/v1/agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Agendas [DELETE /research-sys/api/v1/agendas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Agendas with Matching [DELETE /research-sys/api/v1/agendas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Agenda Id.
    + name (optional) - Agenda Name.
    + typeId (optional) - Agenda typeId.
    + contextId (optional) - Agenda contextId.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + firstItemId (optional) - First Item Id.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
