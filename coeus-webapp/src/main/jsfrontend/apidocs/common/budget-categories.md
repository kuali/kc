## Budget Categories [/research-common/api/v1/budget-categories/]

### Get Budget Categories by Key [GET /research-common/api/v1/budget-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Budget Categories [GET /research-common/api/v1/budget-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Categories with Filtering [GET /research-common/api/v1/budget-categories/]
    
+ Parameters

    + budgetCategoryTypeCode (optional) - Category Type. Maximum length is 3.
    + code (optional) - Budget Category Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Categories [GET /research-common/api/v1/budget-categories/]
	                                          
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
    
            {"columns":["budgetCategoryTypeCode","code","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Budget Categories [GET /research-common/api/v1/budget-categories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Categories.md"
            transfer-encoding:chunked


### Update Budget Categories [PUT /research-common/api/v1/budget-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Categories [PUT /research-common/api/v1/budget-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Categories [POST /research-common/api/v1/budget-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Categories [POST /research-common/api/v1/budget-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryTypeCode": "(val)","code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Categories by Key [DELETE /research-common/api/v1/budget-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Categories [DELETE /research-common/api/v1/budget-categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Categories with Matching [DELETE /research-common/api/v1/budget-categories/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetCategoryTypeCode (optional) - Category Type. Maximum length is 3.
    + code (optional) - Budget Category Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
