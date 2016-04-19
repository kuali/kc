## Entities [/research-sys/api/v1/entities/]

### Get Entities by Key [GET /research-sys/api/v1/entities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Entities [GET /research-sys/api/v1/entities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entities with Filtering [GET /research-sys/api/v1/entities/]
    
+ Parameters

    + id (optional) - 
    + active (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entities [GET /research-sys/api/v1/entities/]
	                                          
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
    
            {"columns":["id","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entities [GET /research-sys/api/v1/entities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entities.md"
            transfer-encoding:chunked


### Update Entities [PUT /research-sys/api/v1/entities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entities [PUT /research-sys/api/v1/entities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entities [POST /research-sys/api/v1/entities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entities [POST /research-sys/api/v1/entities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entities by Key [DELETE /research-sys/api/v1/entities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entities [DELETE /research-sys/api/v1/entities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entities with Matching [DELETE /research-sys/api/v1/entities/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
