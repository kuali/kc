## Award Budgets [/award/api/v1/award-budgets/]

### Get Award Budgets by Key [GET /award/api/v1/award-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","awardBudgetDocument.documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Budgets [GET /award/api/v1/award-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","awardBudgetDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","awardBudgetDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budgets with Filtering [GET /award/api/v1/award-budgets/]
    
+ Parameters

    + budgetId (optional) - Budget Id. Maximum length is 22.
    + awardBudgetStatusCode (optional) - Award Budget Status Code. Maximum length is 3.
    + awardBudgetTypeCode (optional) - Award Budget Type Code. Maximum length is 3.
    + obligatedAmount (optional) - Obligated amount from award amount info. Maximum length is 15.
    + obligatedTotal (optional) - 
    + description (optional) - Description. Maximum length is 255.
    + budgetInitiator (optional) - Budget Initiator. Maximum length is 60.
    + awardId (optional) - 
    + awardBudgetDocument.documentNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","awardBudgetDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","awardBudgetStatusCode": "(val)","awardBudgetTypeCode": "(val)","obligatedAmount": "(val)","obligatedTotal": "(val)","description": "(val)","budgetInitiator": "(val)","awardId": "(val)","awardBudgetDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budgets [GET /award/api/v1/award-budgets/]
	                                          
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
    
            {"columns":["budgetId","awardBudgetStatusCode","awardBudgetTypeCode","obligatedAmount","obligatedTotal","description","budgetInitiator","awardId","awardBudgetDocument.documentNumber"],"primaryKey":"budgetId"}
		
### Get Blueprint API specification for Award Budgets [GET /award/api/v1/award-budgets/]
	 
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
