## Modular Budgets [/propdev/api/v1/modular-budgets/]

### Get Modular Budgets by Key [GET /propdev/api/v1/modular-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}

### Get All Modular Budgets [GET /propdev/api/v1/modular-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
            ]

### Get All Modular Budgets with Filtering [GET /propdev/api/v1/modular-budgets/]
    
+ Parameters

    + budgetPeriodId (optional) - Budget Period Id.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + directCostLessConsortiumFna (optional) - Direct Cost Less Consortium FNA. Maximum length is 15.
    + consortiumFna (optional) - Consortium FNA. Maximum length is 15.
    + totalDirectCost (optional) - Total Direct Cost. Maximum length is 15.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Modular Budgets [GET /propdev/api/v1/modular-budgets/]
	                                          
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
    
            {"columns":["budgetPeriodId","budgetId","budgetPeriod","directCostLessConsortiumFna","consortiumFna","totalDirectCost"],"primaryKey":"budgetPeriodObj"}
		
### Get Blueprint API specification for Modular Budgets [GET /propdev/api/v1/modular-budgets/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Modular Budgets.md"
            transfer-encoding:chunked


### Update Modular Budgets [PUT /propdev/api/v1/modular-budgets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Modular Budgets [PUT /propdev/api/v1/modular-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Modular Budgets [POST /propdev/api/v1/modular-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Modular Budgets [POST /propdev/api/v1/modular-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetId": "(val)","budgetPeriod": "(val)","directCostLessConsortiumFna": "(val)","consortiumFna": "(val)","totalDirectCost": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Modular Budgets by Key [DELETE /propdev/api/v1/modular-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Modular Budgets [DELETE /propdev/api/v1/modular-budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Modular Budgets with Matching [DELETE /propdev/api/v1/modular-budgets/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetPeriodId (optional) - Budget Period Id.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + directCostLessConsortiumFna (optional) - Direct Cost Less Consortium FNA. Maximum length is 15.
    + consortiumFna (optional) - Consortium FNA. Maximum length is 15.
    + totalDirectCost (optional) - Total Direct Cost. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
