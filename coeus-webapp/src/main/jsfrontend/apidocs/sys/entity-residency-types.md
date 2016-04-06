## Entity Residency Types [/research-sys/api/v1/entity-residency-types/]

### Get Entity Residency Types by Key [GET /research-sys/api/v1/entity-residency-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Residency Types [GET /research-sys/api/v1/entity-residency-types/]
	 
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

### Get All Entity Residency Types with Filtering [GET /research-sys/api/v1/entity-residency-types/]
	 
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
			
### Get Schema for Entity Residency Types [GET /research-sys/api/v1/entity-residency-types/]
	 
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
		
### Get Blueprint API specification for Entity Residency Types [GET /research-sys/api/v1/entity-residency-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Residency Types.md"
            transfer-encoding:chunked


### Update Entity Residency Types [PUT /research-sys/api/v1/entity-residency-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Residency Types [PUT /research-sys/api/v1/entity-residency-types/]

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

### Insert Entity Residency Types [POST /research-sys/api/v1/entity-residency-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Residency Types [POST /research-sys/api/v1/entity-residency-types/]

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
            
### Delete Entity Residency Types by Key [DELETE /research-sys/api/v1/entity-residency-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Residency Types [DELETE /research-sys/api/v1/entity-residency-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Residency Types with Matching [DELETE /research-sys/api/v1/entity-residency-types/]
	 
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
