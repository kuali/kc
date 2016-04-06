## Entity Employment Types [/research-sys/api/v1/entity-employment-types/]

### Get Entity Employment Types by Key [GET /research-sys/api/v1/entity-employment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Employment Types [GET /research-sys/api/v1/entity-employment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Employment Types with Filtering [GET /research-sys/api/v1/entity-employment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + name
            + active
            + code
            + sortCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Employment Types [GET /research-sys/api/v1/entity-employment-types/]
	 
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
		
### Get Blueprint API specification for Entity Employment Types [GET /research-sys/api/v1/entity-employment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Employment Types.md"
            transfer-encoding:chunked


### Update Entity Employment Types [PUT /research-sys/api/v1/entity-employment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Employment Types [PUT /research-sys/api/v1/entity-employment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Employment Types [POST /research-sys/api/v1/entity-employment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Employment Types [POST /research-sys/api/v1/entity-employment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Employment Types by Key [DELETE /research-sys/api/v1/entity-employment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Employment Types [DELETE /research-sys/api/v1/entity-employment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Employment Types with Matching [DELETE /research-sys/api/v1/entity-employment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + name
            + active
            + code
            + sortCode


+ Response 204
