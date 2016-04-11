## Budget Project Incomes [/research-sys/api/v1/budget-project-incomes/]

### Get Budget Project Incomes by Key [GET /research-sys/api/v1/budget-project-incomes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Project Incomes [GET /research-sys/api/v1/budget-project-incomes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Project Incomes with Filtering [GET /research-sys/api/v1/budget-project-incomes/]
    
+ Parameters

        + documentComponentId
            + budgetId
            + budgetPeriodId
            + budgetPeriodNumber
            + description
            + projectIncome
            + hierarchyProposalNumber
            + hiddenInHierarchy

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Project Incomes [GET /research-sys/api/v1/budget-project-incomes/]
	                                          
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
    
            {"columns":["documentComponentId","budgetId","budgetPeriodId","budgetPeriodNumber","description","projectIncome","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"budgetId:budgetPeriod:documentComponentId"}
		
### Get Blueprint API specification for Budget Project Incomes [GET /research-sys/api/v1/budget-project-incomes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Project Incomes.md"
            transfer-encoding:chunked


### Update Budget Project Incomes [PUT /research-sys/api/v1/budget-project-incomes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Project Incomes [PUT /research-sys/api/v1/budget-project-incomes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Project Incomes [POST /research-sys/api/v1/budget-project-incomes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Project Incomes [POST /research-sys/api/v1/budget-project-incomes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Project Incomes by Key [DELETE /research-sys/api/v1/budget-project-incomes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Project Incomes [DELETE /research-sys/api/v1/budget-project-incomes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Project Incomes with Matching [DELETE /research-sys/api/v1/budget-project-incomes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentComponentId
            + budgetId
            + budgetPeriodId
            + budgetPeriodNumber
            + description
            + projectIncome
            + hierarchyProposalNumber
            + hiddenInHierarchy

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
