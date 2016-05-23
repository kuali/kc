## Rate Class Types [/research-common/api/v1/rate-class-types/]

### Get Rate Class Types by Key [GET /research-common/api/v1/rate-class-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}

### Get All Rate Class Types [GET /research-common/api/v1/rate-class-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rate Class Types with Filtering [GET /research-common/api/v1/rate-class-types/]
    
+ Parameters

    + code (optional) - Rate Class Type. Maximum length is 1.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - This sort id is used for sorting budget category. Maximum length is 2.
    + prefixActivityType (optional) - Prefix Activity Type in rates page. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rate Class Types [GET /research-common/api/v1/rate-class-types/]
	                                          
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
    
            {"columns":["code","description","sortId","prefixActivityType"],"primaryKey":"code"}
		
### Get Blueprint API specification for Rate Class Types [GET /research-common/api/v1/rate-class-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Class Types.md"
            transfer-encoding:chunked
### Update Rate Class Types [PUT /research-common/api/v1/rate-class-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Class Types [PUT /research-common/api/v1/rate-class-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Rate Class Types [POST /research-common/api/v1/rate-class-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Class Types [POST /research-common/api/v1/rate-class-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","prefixActivityType": "(val)","_primaryKey": "(val)"}
            ]
### Delete Rate Class Types by Key [DELETE /research-common/api/v1/rate-class-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Types [DELETE /research-common/api/v1/rate-class-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Types with Matching [DELETE /research-common/api/v1/rate-class-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Rate Class Type. Maximum length is 1.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - This sort id is used for sorting budget category. Maximum length is 2.
    + prefixActivityType (optional) - Prefix Activity Type in rates page. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
