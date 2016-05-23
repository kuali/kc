## Kew Types [/research-sys/api/v1/kew-types/]

### Get Kew Types by Key [GET /research-sys/api/v1/kew-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kew Types [GET /research-sys/api/v1/kew-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kew Types with Filtering [GET /research-sys/api/v1/kew-types/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + namespace (optional) - 
    + serviceName (optional) - 
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
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kew Types [GET /research-sys/api/v1/kew-types/]
	                                          
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
    
            {"columns":["id","name","namespace","serviceName","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Kew Types [GET /research-sys/api/v1/kew-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kew Types.md"
            transfer-encoding:chunked
### Update Kew Types [PUT /research-sys/api/v1/kew-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kew Types [PUT /research-sys/api/v1/kew-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Kew Types [POST /research-sys/api/v1/kew-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kew Types [POST /research-sys/api/v1/kew-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","serviceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Kew Types by Key [DELETE /research-sys/api/v1/kew-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Types [DELETE /research-sys/api/v1/kew-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Types with Matching [DELETE /research-sys/api/v1/kew-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + namespace (optional) - 
    + serviceName (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
