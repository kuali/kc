## Award Budget Types [/award/api/v1/award-budget-types/]

### Get Award Budget Types by Key [GET /award/api/v1/award-budget-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Types [GET /award/api/v1/award-budget-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Types with Filtering [GET /award/api/v1/award-budget-types/]
    
+ Parameters

    + awardBudgetTypeCode (optional) - Award Budget Type Code. Maximum length is 3.
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
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Types [GET /award/api/v1/award-budget-types/]
	                                          
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
    
            {"columns":["awardBudgetTypeCode","description"],"primaryKey":"awardBudgetTypeCode"}
		
### Get Blueprint API specification for Award Budget Types [GET /award/api/v1/award-budget-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Types.md"
            transfer-encoding:chunked
### Update Award Budget Types [PUT /award/api/v1/award-budget-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Types [PUT /award/api/v1/award-budget-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Budget Types [POST /award/api/v1/award-budget-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Types [POST /award/api/v1/award-budget-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Budget Types by Key [DELETE /award/api/v1/award-budget-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Types [DELETE /award/api/v1/award-budget-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Types with Matching [DELETE /award/api/v1/award-budget-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardBudgetTypeCode (optional) - Award Budget Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
