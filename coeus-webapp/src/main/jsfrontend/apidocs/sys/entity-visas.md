## Entity Visas [/research-sys/api/v1/entity-visas/]

### Get Entity Visas by Key [GET /research-sys/api/v1/entity-visas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}

### Get All Entity Visas [GET /research-sys/api/v1/entity-visas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Visas with Filtering [GET /research-sys/api/v1/entity-visas/]
    
+ Parameters

    + id (optional) - 
    + entityId (optional) - 
    + visaTypeKey (optional) - 
    + visaEntry (optional) - 
    + visaId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Visas [GET /research-sys/api/v1/entity-visas/]
	                                          
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
    
            {"columns":["id","entityId","visaTypeKey","visaEntry","visaId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Visas [GET /research-sys/api/v1/entity-visas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Visas.md"
            transfer-encoding:chunked
### Update Entity Visas [PUT /research-sys/api/v1/entity-visas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Visas [PUT /research-sys/api/v1/entity-visas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Visas [POST /research-sys/api/v1/entity-visas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Visas [POST /research-sys/api/v1/entity-visas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","visaTypeKey": "(val)","visaEntry": "(val)","visaId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Visas by Key [DELETE /research-sys/api/v1/entity-visas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Visas [DELETE /research-sys/api/v1/entity-visas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Visas with Matching [DELETE /research-sys/api/v1/entity-visas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + entityId (optional) - 
    + visaTypeKey (optional) - 
    + visaEntry (optional) - 
    + visaId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
