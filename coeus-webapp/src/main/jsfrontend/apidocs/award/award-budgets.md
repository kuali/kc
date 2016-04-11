## Award Budgets [/research-sys/api/v1/award-budgets/]

### Get Award Budgets by Key [GET /research-sys/api/v1/award-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Budgets [GET /research-sys/api/v1/award-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budgets with Filtering [GET /research-sys/api/v1/award-budgets/]
    
+ Parameters

        + budgetId
            + documentNumber
            + awardBudgetStatusCode
            + awardBudgetTypeCode
            + obligatedAmount
            + obligatedTotal
            + description
            + budgetInitiator
            + awardId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budgets [GET /research-sys/api/v1/award-budgets/]
	                                          
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
    
            {"columns":["budgetId","documentNumber","awardBudgetStatusCode","awardBudgetTypeCode","obligatedAmount","obligatedTotal","description","budgetInitiator","awardId"],"primaryKey":"budgetId"}
		
### Get Blueprint API specification for Award Budgets [GET /research-sys/api/v1/award-budgets/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budgets.md"
            transfer-encoding:chunked


### Update Award Budgets [PUT /research-sys/api/v1/award-budgets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budgets [PUT /research-sys/api/v1/award-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budgets [POST /research-sys/api/v1/award-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budgets [POST /research-sys/api/v1/award-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budgets by Key [DELETE /research-sys/api/v1/award-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budgets [DELETE /research-sys/api/v1/award-budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budgets with Matching [DELETE /research-sys/api/v1/award-budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetId
            + documentNumber
            + awardBudgetStatusCode
            + awardBudgetTypeCode
            + obligatedAmount
            + obligatedTotal
            + description
            + budgetInitiator
            + awardId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
