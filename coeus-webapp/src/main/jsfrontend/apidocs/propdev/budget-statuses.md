## Budget Statuses [/propdev/api/v1/budget-statuses/]

### Get Budget Statuses by Key [GET /propdev/api/v1/budget-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Budget Statuses [GET /propdev/api/v1/budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Statuses with Filtering [GET /propdev/api/v1/budget-statuses/]
    
+ Parameters

    + budgetStatusCode (optional) - Budget Status Code. Maximum length is 3.
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
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Statuses [GET /propdev/api/v1/budget-statuses/]
	                                          
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
    
            {"columns":["budgetStatusCode","description"],"primaryKey":"budgetStatusCode"}
		
### Get Blueprint API specification for Budget Statuses [GET /propdev/api/v1/budget-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Statuses.md"
            transfer-encoding:chunked


### Update Budget Statuses [PUT /propdev/api/v1/budget-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Statuses [PUT /propdev/api/v1/budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Statuses [POST /propdev/api/v1/budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Statuses [POST /propdev/api/v1/budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Statuses by Key [DELETE /propdev/api/v1/budget-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Statuses [DELETE /propdev/api/v1/budget-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Statuses with Matching [DELETE /propdev/api/v1/budget-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetStatusCode (optional) - Budget Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
