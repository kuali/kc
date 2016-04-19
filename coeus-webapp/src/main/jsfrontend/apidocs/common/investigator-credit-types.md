## Investigator Credit Types [/research-common/api/v1/investigator-credit-types/]

### Get Investigator Credit Types by Key [GET /research-common/api/v1/investigator-credit-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Investigator Credit Types [GET /research-common/api/v1/investigator-credit-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Investigator Credit Types with Filtering [GET /research-common/api/v1/investigator-credit-types/]
    
+ Parameters

    + code (optional) - Investigator Credit Type Code. Maximum length is 3.
    + addsToHundred (optional) - Adds to Hundred. Maximum length is 1.
    + active (optional) - Is Active. Maximum length is 1.
    + description (optional) - Description. Maximum length is 300.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Investigator Credit Types [GET /research-common/api/v1/investigator-credit-types/]
	                                          
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
    
            {"columns":["code","addsToHundred","active","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Investigator Credit Types [GET /research-common/api/v1/investigator-credit-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Investigator Credit Types.md"
            transfer-encoding:chunked


### Update Investigator Credit Types [PUT /research-common/api/v1/investigator-credit-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Investigator Credit Types [PUT /research-common/api/v1/investigator-credit-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Investigator Credit Types [POST /research-common/api/v1/investigator-credit-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Investigator Credit Types [POST /research-common/api/v1/investigator-credit-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Investigator Credit Types by Key [DELETE /research-common/api/v1/investigator-credit-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Investigator Credit Types [DELETE /research-common/api/v1/investigator-credit-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Investigator Credit Types with Matching [DELETE /research-common/api/v1/investigator-credit-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Investigator Credit Type Code. Maximum length is 3.
    + addsToHundred (optional) - Adds to Hundred. Maximum length is 1.
    + active (optional) - Is Active. Maximum length is 1.
    + description (optional) - Description. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
