## Protocol Affiliation Types [/research-sys/api/v1/protocol-affiliation-types/]

### Get Protocol Affiliation Types by Key [GET /research-sys/api/v1/protocol-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Protocol Affiliation Types [GET /research-sys/api/v1/protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Affiliation Types with Filtering [GET /research-sys/api/v1/protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + affiliationTypeCode
            + description
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Affiliation Types [GET /research-sys/api/v1/protocol-affiliation-types/]
	 
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
		
### Get Blueprint API specification for Protocol Affiliation Types [GET /research-sys/api/v1/protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Affiliation Types.md"
            transfer-encoding:chunked


### Update Protocol Affiliation Types [PUT /research-sys/api/v1/protocol-affiliation-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Affiliation Types [PUT /research-sys/api/v1/protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Affiliation Types [POST /research-sys/api/v1/protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Affiliation Types [POST /research-sys/api/v1/protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Affiliation Types by Key [DELETE /research-sys/api/v1/protocol-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Affiliation Types [DELETE /research-sys/api/v1/protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Affiliation Types with Matching [DELETE /research-sys/api/v1/protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + affiliationTypeCode
            + description
            + active


+ Response 204
