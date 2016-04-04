## Entity Types [/research-sys/api/v1/entity-types/]

### Get Entity Types by Key [GET /research-sys/api/v1/entity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Types [GET /research-sys/api/v1/entity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Types with Filtering [GET /research-sys/api/v1/entity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + code
            + name
            + active
            + sortCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Types [GET /research-sys/api/v1/entity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Entity Types [GET /research-sys/api/v1/entity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Types.md"
            transfer-encoding:chunked


### Update Entity Types [PUT /research-sys/api/v1/entity-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Types [PUT /research-sys/api/v1/entity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Types [POST /research-sys/api/v1/entity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Types [POST /research-sys/api/v1/entity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Types by Key [DELETE /research-sys/api/v1/entity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Types [DELETE /research-sys/api/v1/entity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Types with Matching [DELETE /research-sys/api/v1/entity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + code
            + name
            + active
            + sortCode


+ Response 204
