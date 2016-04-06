## Correspondent Types [/research-sys/api/v1/correspondent-types/]

### Get Correspondent Types by Key [GET /research-sys/api/v1/correspondent-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}

### Get All Correspondent Types [GET /research-sys/api/v1/correspondent-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]

### Get All Correspondent Types with Filtering [GET /research-sys/api/v1/correspondent-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + correspondentTypeCode
            + description
            + qualifier
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Correspondent Types [GET /research-sys/api/v1/correspondent-types/]
	 
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
		
### Get Blueprint API specification for Correspondent Types [GET /research-sys/api/v1/correspondent-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Correspondent Types.md"
            transfer-encoding:chunked


### Update Correspondent Types [PUT /research-sys/api/v1/correspondent-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Correspondent Types [PUT /research-sys/api/v1/correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Correspondent Types [POST /research-sys/api/v1/correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Correspondent Types [POST /research-sys/api/v1/correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Correspondent Types by Key [DELETE /research-sys/api/v1/correspondent-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Correspondent Types [DELETE /research-sys/api/v1/correspondent-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Correspondent Types with Matching [DELETE /research-sys/api/v1/correspondent-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + correspondentTypeCode
            + description
            + qualifier


+ Response 204
