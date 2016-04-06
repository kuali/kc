## Iacuc Protocol Review Types [/research-sys/api/v1/iacuc-protocol-review-types/]

### Get Iacuc Protocol Review Types by Key [GET /research-sys/api/v1/iacuc-protocol-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Review Types [GET /research-sys/api/v1/iacuc-protocol-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Review Types with Filtering [GET /research-sys/api/v1/iacuc-protocol-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + reviewTypeCode
            + description
            + globalFlag
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Review Types [GET /research-sys/api/v1/iacuc-protocol-review-types/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Review Types [GET /research-sys/api/v1/iacuc-protocol-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Review Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Review Types [PUT /research-sys/api/v1/iacuc-protocol-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Review Types [PUT /research-sys/api/v1/iacuc-protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Review Types [POST /research-sys/api/v1/iacuc-protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Review Types [POST /research-sys/api/v1/iacuc-protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Review Types by Key [DELETE /research-sys/api/v1/iacuc-protocol-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Types [DELETE /research-sys/api/v1/iacuc-protocol-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Review Types with Matching [DELETE /research-sys/api/v1/iacuc-protocol-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + reviewTypeCode
            + description
            + globalFlag


+ Response 204
