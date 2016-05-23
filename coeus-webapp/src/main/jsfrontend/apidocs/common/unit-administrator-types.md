## Unit Administrator Types [/research-common/api/v1/unit-administrator-types/]

### Get Unit Administrator Types by Key [GET /research-common/api/v1/unit-administrator-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}

### Get All Unit Administrator Types [GET /research-common/api/v1/unit-administrator-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Unit Administrator Types with Filtering [GET /research-common/api/v1/unit-administrator-types/]
    
+ Parameters

    + code (optional) - Unit Administrator Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + multiplesFlag (optional) - Multiples Flag. Maximum length is 3.
    + defaultGroupFlag (optional) - Default Group Flag. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Administrator Types [GET /research-common/api/v1/unit-administrator-types/]
	                                          
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
    
            {"columns":["code","description","multiplesFlag","defaultGroupFlag"],"primaryKey":"code"}
		
### Get Blueprint API specification for Unit Administrator Types [GET /research-common/api/v1/unit-administrator-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Administrator Types.md"
            transfer-encoding:chunked
### Update Unit Administrator Types [PUT /research-common/api/v1/unit-administrator-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Administrator Types [PUT /research-common/api/v1/unit-administrator-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Unit Administrator Types [POST /research-common/api/v1/unit-administrator-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Administrator Types [POST /research-common/api/v1/unit-administrator-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
### Delete Unit Administrator Types by Key [DELETE /research-common/api/v1/unit-administrator-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrator Types [DELETE /research-common/api/v1/unit-administrator-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrator Types with Matching [DELETE /research-common/api/v1/unit-administrator-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Unit Administrator Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + multiplesFlag (optional) - Multiples Flag. Maximum length is 3.
    + defaultGroupFlag (optional) - Default Group Flag. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
