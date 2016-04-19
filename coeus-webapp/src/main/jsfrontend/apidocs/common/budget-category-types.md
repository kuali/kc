## Budget Category Types [/research-common/api/v1/budget-category-types/]

### Get Budget Category Types by Key [GET /research-common/api/v1/budget-category-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Budget Category Types [GET /research-common/api/v1/budget-category-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Category Types with Filtering [GET /research-common/api/v1/budget-category-types/]
    
+ Parameters

    + code (optional) - Budget Category Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - This sort id is used for sorting budget category. Maximum length is 2.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Category Types [GET /research-common/api/v1/budget-category-types/]
	                                          
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
    
            {"columns":["code","description","sortId"],"primaryKey":"code"}
		
### Get Blueprint API specification for Budget Category Types [GET /research-common/api/v1/budget-category-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Category Types.md"
            transfer-encoding:chunked


### Update Budget Category Types [PUT /research-common/api/v1/budget-category-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Category Types [PUT /research-common/api/v1/budget-category-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Category Types [POST /research-common/api/v1/budget-category-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Category Types [POST /research-common/api/v1/budget-category-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Category Types by Key [DELETE /research-common/api/v1/budget-category-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Types [DELETE /research-common/api/v1/budget-category-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Types with Matching [DELETE /research-common/api/v1/budget-category-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Budget Category Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - This sort id is used for sorting budget category. Maximum length is 2.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
