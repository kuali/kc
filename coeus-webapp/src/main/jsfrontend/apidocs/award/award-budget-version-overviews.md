## Award Budget Version Overviews [/research-sys/api/v1/award-budget-version-overviews/]

### Get Award Budget Version Overviews by Key [GET /research-sys/api/v1/award-budget-version-overviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Version Overviews [GET /research-sys/api/v1/award-budget-version-overviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Version Overviews with Filtering [GET /research-sys/api/v1/award-budget-version-overviews/]
    
+ Parameters

        + budgetId
            + awardBudgetStatusCode
            + awardBudgetTypeCode
            + obligatedAmount
            + description
            + budgetInitiator

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Version Overviews [GET /research-sys/api/v1/award-budget-version-overviews/]
	                                          
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
    
            {"columns":["budgetId","awardBudgetStatusCode","awardBudgetTypeCode","obligatedAmount","description","budgetInitiator"],"primaryKey":"budgetId"}
		
### Get Blueprint API specification for Award Budget Version Overviews [GET /research-sys/api/v1/award-budget-version-overviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Version Overviews.md"
            transfer-encoding:chunked


### Update Award Budget Version Overviews [PUT /research-sys/api/v1/award-budget-version-overviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Version Overviews [PUT /research-sys/api/v1/award-budget-version-overviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Version Overviews [POST /research-sys/api/v1/award-budget-version-overviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Version Overviews [POST /research-sys/api/v1/award-budget-version-overviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","description": "(val)","budgetInitiator": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Version Overviews by Key [DELETE /research-sys/api/v1/award-budget-version-overviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Version Overviews [DELETE /research-sys/api/v1/award-budget-version-overviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Version Overviews with Matching [DELETE /research-sys/api/v1/award-budget-version-overviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetId
            + awardBudgetStatusCode
            + awardBudgetTypeCode
            + obligatedAmount
            + description
            + budgetInitiator

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
