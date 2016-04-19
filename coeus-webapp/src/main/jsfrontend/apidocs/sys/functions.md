## Functions [/research-sys/api/v1/functions/]

### Get Functions by Key [GET /research-sys/api/v1/functions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Functions [GET /research-sys/api/v1/functions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Functions with Filtering [GET /research-sys/api/v1/functions/]
    
+ Parameters

    + id (optional) - 
    + namespace (optional) - 
    + name (optional) - 
    + description (optional) - 
    + returnType (optional) - 
    + typeId (optional) - 
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
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Functions [GET /research-sys/api/v1/functions/]
	                                          
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
    
            {"columns":["id","namespace","name","description","returnType","typeId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Functions [GET /research-sys/api/v1/functions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Functions.md"
            transfer-encoding:chunked


### Update Functions [PUT /research-sys/api/v1/functions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Functions [PUT /research-sys/api/v1/functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Functions [POST /research-sys/api/v1/functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Functions [POST /research-sys/api/v1/functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","returnType": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Functions by Key [DELETE /research-sys/api/v1/functions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Functions [DELETE /research-sys/api/v1/functions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Functions with Matching [DELETE /research-sys/api/v1/functions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + namespace (optional) - 
    + name (optional) - 
    + description (optional) - 
    + returnType (optional) - 
    + typeId (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
