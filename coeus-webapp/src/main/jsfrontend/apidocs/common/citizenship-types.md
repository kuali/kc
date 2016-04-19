## Citizenship Types [/research-common/api/v1/citizenship-types/]

### Get Citizenship Types by Key [GET /research-common/api/v1/citizenship-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Citizenship Types [GET /research-common/api/v1/citizenship-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Citizenship Types with Filtering [GET /research-common/api/v1/citizenship-types/]
    
+ Parameters

    + code (optional) - Citizenship Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 40.
    + active (optional) - Active.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Citizenship Types [GET /research-common/api/v1/citizenship-types/]
	                                          
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
    
            {"columns":["code","description","active"],"primaryKey":"code"}
		
### Get Blueprint API specification for Citizenship Types [GET /research-common/api/v1/citizenship-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Citizenship Types.md"
            transfer-encoding:chunked


### Update Citizenship Types [PUT /research-common/api/v1/citizenship-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Citizenship Types [PUT /research-common/api/v1/citizenship-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Citizenship Types [POST /research-common/api/v1/citizenship-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Citizenship Types [POST /research-common/api/v1/citizenship-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Citizenship Types by Key [DELETE /research-common/api/v1/citizenship-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Citizenship Types [DELETE /research-common/api/v1/citizenship-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Citizenship Types with Matching [DELETE /research-common/api/v1/citizenship-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Citizenship Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 40.
    + active (optional) - Active.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
