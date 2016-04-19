## Budget Project Incomes [/research-common/api/v1/budget-project-incomes/]

### Get Budget Project Incomes by Key [GET /research-common/api/v1/budget-project-incomes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Project Incomes [GET /research-common/api/v1/budget-project-incomes/]
	 
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

### Get All Budget Project Incomes with Filtering [GET /research-common/api/v1/budget-project-incomes/]
    
+ Parameters

    + documentComponentId (optional) - Document Component Id. Maximum length is 5.
    + budgetId (optional) - Budget Id.
    + budgetPeriodId (optional) - Budget Period Id.
    + budgetPeriodNumber (optional) - Budget Period. Maximum length is 3.
    + description (optional) - Description. Maximum length is 2000.
    + projectIncome (optional) - Project Income. Maximum length is 15.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

            
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
			
### Get Schema for Budget Project Incomes [GET /research-common/api/v1/budget-project-incomes/]
	                                          
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
		
### Get Blueprint API specification for Budget Project Incomes [GET /research-common/api/v1/budget-project-incomes/]
	 
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


### Update Budget Project Incomes [PUT /research-common/api/v1/budget-project-incomes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Project Incomes [PUT /research-common/api/v1/budget-project-incomes/]

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

### Insert Budget Project Incomes [POST /research-common/api/v1/budget-project-incomes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentComponentId": "(val)","budgetId": "(val)","budgetPeriodId": "(val)","budgetPeriodNumber": "(val)","description": "(val)","projectIncome": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Project Incomes [POST /research-common/api/v1/budget-project-incomes/]

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
            
### Delete Budget Project Incomes by Key [DELETE /research-common/api/v1/budget-project-incomes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Project Incomes [DELETE /research-common/api/v1/budget-project-incomes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Project Incomes with Matching [DELETE /research-common/api/v1/budget-project-incomes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + documentComponentId (optional) - Document Component Id. Maximum length is 5.
    + budgetId (optional) - Budget Id.
    + budgetPeriodId (optional) - Budget Period Id.
    + budgetPeriodNumber (optional) - Budget Period. Maximum length is 3.
    + description (optional) - Description. Maximum length is 2000.
    + projectIncome (optional) - Project Income. Maximum length is 15.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
