## Iacuc Correspondent Types [/research-sys/api/v1/iacuc-correspondent-types/]

### Get Iacuc Correspondent Types by Key [GET /research-sys/api/v1/iacuc-correspondent-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Correspondent Types [GET /research-sys/api/v1/iacuc-correspondent-types/]
	 
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

### Get All Iacuc Correspondent Types with Filtering [GET /research-sys/api/v1/iacuc-correspondent-types/]
    
+ Parameters

        + correspondentTypeCode
            + description
            + qualifier

            
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
			
### Get Schema for Iacuc Correspondent Types [GET /research-sys/api/v1/iacuc-correspondent-types/]
	                                          
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
    
            {"columns":["correspondentTypeCode","description","qualifier"],"primaryKey":"correspondentTypeCode"}
		
### Get Blueprint API specification for Iacuc Correspondent Types [GET /research-sys/api/v1/iacuc-correspondent-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Correspondent Types.md"
            transfer-encoding:chunked


### Update Iacuc Correspondent Types [PUT /research-sys/api/v1/iacuc-correspondent-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Correspondent Types [PUT /research-sys/api/v1/iacuc-correspondent-types/]

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

### Insert Iacuc Correspondent Types [POST /research-sys/api/v1/iacuc-correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Correspondent Types [POST /research-sys/api/v1/iacuc-correspondent-types/]

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
            
### Delete Iacuc Correspondent Types by Key [DELETE /research-sys/api/v1/iacuc-correspondent-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Correspondent Types [DELETE /research-sys/api/v1/iacuc-correspondent-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Correspondent Types with Matching [DELETE /research-sys/api/v1/iacuc-correspondent-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + correspondentTypeCode
            + description
            + qualifier

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
