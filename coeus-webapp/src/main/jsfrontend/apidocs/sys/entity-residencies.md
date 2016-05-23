## Entity Residencies [/research-sys/api/v1/entity-residencies/]

### Get Entity Residencies by Key [GET /research-sys/api/v1/entity-residencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}

### Get All Entity Residencies [GET /research-sys/api/v1/entity-residencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Residencies with Filtering [GET /research-sys/api/v1/entity-residencies/]
    
+ Parameters

    + id (optional) - 
    + entityId (optional) - 
    + determinationMethod (optional) - 
    + inState (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Residencies [GET /research-sys/api/v1/entity-residencies/]
	                                          
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
    
            {"columns":["id","entityId","determinationMethod","inState"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Residencies [GET /research-sys/api/v1/entity-residencies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Residencies.md"
            transfer-encoding:chunked
### Update Entity Residencies [PUT /research-sys/api/v1/entity-residencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Residencies [PUT /research-sys/api/v1/entity-residencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Residencies [POST /research-sys/api/v1/entity-residencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Residencies [POST /research-sys/api/v1/entity-residencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","determinationMethod": "(val)","inState": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Residencies by Key [DELETE /research-sys/api/v1/entity-residencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Residencies [DELETE /research-sys/api/v1/entity-residencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Residencies with Matching [DELETE /research-sys/api/v1/entity-residencies/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + entityId (optional) - 
    + determinationMethod (optional) - 
    + inState (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
