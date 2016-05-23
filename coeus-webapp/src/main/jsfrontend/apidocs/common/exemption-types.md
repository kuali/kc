## Exemption Types [/research-common/api/v1/exemption-types/]

### Get Exemption Types by Key [GET /research-common/api/v1/exemption-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}

### Get All Exemption Types [GET /research-common/api/v1/exemption-types/]
	 
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

### Get All Exemption Types with Filtering [GET /research-common/api/v1/exemption-types/]
    
+ Parameters

    + code (optional) - Exemption Type. Maximum length is 3.
    + description (optional) - This is the exemption number. Maximum length is 200.
    + detailedDescription (optional) - This is the detailed description of the exemption number.  Source is 45cfr46.101(b). Maximum length is 2000.

            
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
			
### Get Schema for Exemption Types [GET /research-common/api/v1/exemption-types/]
	                                          
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
    
            {"columns":["code","description","detailedDescription"],"primaryKey":"code"}
		
### Get Blueprint API specification for Exemption Types [GET /research-common/api/v1/exemption-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Exemption Types.md"
            transfer-encoding:chunked
### Update Exemption Types [PUT /research-common/api/v1/exemption-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Exemption Types [PUT /research-common/api/v1/exemption-types/]

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
### Insert Exemption Types [POST /research-common/api/v1/exemption-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","detailedDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Exemption Types [POST /research-common/api/v1/exemption-types/]

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
### Delete Exemption Types by Key [DELETE /research-common/api/v1/exemption-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Exemption Types [DELETE /research-common/api/v1/exemption-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Exemption Types with Matching [DELETE /research-common/api/v1/exemption-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Exemption Type. Maximum length is 3.
    + description (optional) - This is the exemption number. Maximum length is 200.
    + detailedDescription (optional) - This is the detailed description of the exemption number.  Source is 45cfr46.101(b). Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
