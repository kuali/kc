## Budget Formulated Cost Details [/research-sys/api/v1/budget-formulated-cost-details/]

### Get Budget Formulated Cost Details by Key [GET /research-sys/api/v1/budget-formulated-cost-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}

### Get All Budget Formulated Cost Details [GET /research-sys/api/v1/budget-formulated-cost-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"},
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Formulated Cost Details with Filtering [GET /research-sys/api/v1/budget-formulated-cost-details/]
    
+ Parameters

        + budgetFormulatedCostDetailId
            + formulatedNumber
            + formulatedTypeCode
            + unitCost
            + count
            + frequency
            + calculatedExpenses

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"},
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Formulated Cost Details [GET /research-sys/api/v1/budget-formulated-cost-details/]
	                                          
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
    
            {"columns":["budgetFormulatedCostDetailId","formulatedNumber","formulatedTypeCode","unitCost","count","frequency","calculatedExpenses"],"primaryKey":"budgetFormulatedCostDetailId"}
		
### Get Blueprint API specification for Budget Formulated Cost Details [GET /research-sys/api/v1/budget-formulated-cost-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Formulated Cost Details.md"
            transfer-encoding:chunked


### Update Budget Formulated Cost Details [PUT /research-sys/api/v1/budget-formulated-cost-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Formulated Cost Details [PUT /research-sys/api/v1/budget-formulated-cost-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"},
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Formulated Cost Details [POST /research-sys/api/v1/budget-formulated-cost-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Formulated Cost Details [POST /research-sys/api/v1/budget-formulated-cost-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"},
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"},
              {"budgetFormulatedCostDetailId": "(val)","formulatedNumber": "(val)","formulatedTypeCode": "(val)","unitCost": "(val)","count": "(val)","frequency": "(val)","calculatedExpenses": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Formulated Cost Details by Key [DELETE /research-sys/api/v1/budget-formulated-cost-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Formulated Cost Details [DELETE /research-sys/api/v1/budget-formulated-cost-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Formulated Cost Details with Matching [DELETE /research-sys/api/v1/budget-formulated-cost-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetFormulatedCostDetailId
            + formulatedNumber
            + formulatedTypeCode
            + unitCost
            + count
            + frequency
            + calculatedExpenses

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
