## Budget Cost Shares [/research-common/api/v1/budget-cost-shares/]

### Get Budget Cost Shares by Key [GET /research-common/api/v1/budget-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Cost Shares [GET /research-common/api/v1/budget-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Cost Shares with Filtering [GET /research-common/api/v1/budget-cost-shares/]
    
+ Parameters

    + documentComponentId (optional) - Document Component Id. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + projectPeriod (optional) - Project Period. Maximum length is 4.
    + shareAmount (optional) - Share Amount. Maximum length is 15.
    + sharePercentage (optional) - Share Percentage. Maximum length is 6.
    + sourceAccount (optional) - Source Account. Maximum length is 32.
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
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Cost Shares [GET /research-common/api/v1/budget-cost-shares/]
	                                          
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
    
            {"columns":["documentComponentId","budgetId","projectPeriod","shareAmount","sharePercentage","sourceAccount","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"budget:budgetId:documentComponentId"}
		
### Get Blueprint API specification for Budget Cost Shares [GET /research-common/api/v1/budget-cost-shares/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Cost Shares.md"
            transfer-encoding:chunked
### Update Budget Cost Shares [PUT /research-common/api/v1/budget-cost-shares/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Cost Shares [PUT /research-common/api/v1/budget-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Budget Cost Shares [POST /research-common/api/v1/budget-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Cost Shares [POST /research-common/api/v1/budget-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","projectPeriod": "(val)","shareAmount": "(val)","sharePercentage": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
### Delete Budget Cost Shares by Key [DELETE /research-common/api/v1/budget-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Cost Shares [DELETE /research-common/api/v1/budget-cost-shares/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Cost Shares with Matching [DELETE /research-common/api/v1/budget-cost-shares/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + documentComponentId (optional) - Document Component Id. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + projectPeriod (optional) - Project Period. Maximum length is 4.
    + shareAmount (optional) - Share Amount. Maximum length is 15.
    + sharePercentage (optional) - Share Percentage. Maximum length is 6.
    + sourceAccount (optional) - Source Account. Maximum length is 32.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
