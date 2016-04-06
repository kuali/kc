## Exemption Types [/research-sys/api/v1/exemption-types/]

### Get Exemption Types by Key [GET /research-sys/api/v1/exemption-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}

### Get All Exemption Types [GET /research-sys/api/v1/exemption-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Exemption Types with Filtering [GET /research-sys/api/v1/exemption-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + code
            + description
            + detailedDescription
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Exemption Types [GET /research-sys/api/v1/exemption-types/]
	 
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
		
### Get Blueprint API specification for Exemption Types [GET /research-sys/api/v1/exemption-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Exemption Types.md"
            transfer-encoding:chunked


### Update Exemption Types [PUT /research-sys/api/v1/exemption-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Exemption Types [PUT /research-sys/api/v1/exemption-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Exemption Types [POST /research-sys/api/v1/exemption-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Exemption Types [POST /research-sys/api/v1/exemption-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Exemption Types by Key [DELETE /research-sys/api/v1/exemption-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Exemption Types [DELETE /research-sys/api/v1/exemption-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Exemption Types with Matching [DELETE /research-sys/api/v1/exemption-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + code
            + description
            + detailedDescription


+ Response 204
