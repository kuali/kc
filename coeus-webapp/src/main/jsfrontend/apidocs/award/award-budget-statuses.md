## Award Budget Statuses [/award/api/v1/award-budget-statuses/]

### Get Award Budget Statuses by Key [GET /award/api/v1/award-budget-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Statuses [GET /award/api/v1/award-budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Statuses with Filtering [GET /award/api/v1/award-budget-statuses/]
    
+ Parameters

    + awardBudgetStatusCode (optional) - Award Budget Status Code. Maximum length is 3.
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
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Statuses [GET /award/api/v1/award-budget-statuses/]
	                                          
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
    
            {"columns":["awardBudgetStatusCode","description"],"primaryKey":"awardBudgetStatusCode"}
		
### Get Blueprint API specification for Award Budget Statuses [GET /award/api/v1/award-budget-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Statuses.md"
            transfer-encoding:chunked


### Update Award Budget Statuses [PUT /award/api/v1/award-budget-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Statuses [PUT /award/api/v1/award-budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Statuses [POST /award/api/v1/award-budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Statuses [POST /award/api/v1/award-budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Statuses by Key [DELETE /award/api/v1/award-budget-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Statuses [DELETE /award/api/v1/award-budget-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Statuses with Matching [DELETE /award/api/v1/award-budget-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardBudgetStatusCode (optional) - Award Budget Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
