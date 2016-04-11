## Budget Unrecovered F And As [/research-sys/api/v1/budget-unrecovered-f-and-as/]

### Get Budget Unrecovered F And As by Key [GET /research-sys/api/v1/budget-unrecovered-f-and-as/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Unrecovered F And As [GET /research-sys/api/v1/budget-unrecovered-f-and-as/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Unrecovered F And As with Filtering [GET /research-sys/api/v1/budget-unrecovered-f-and-as/]
    
+ Parameters

        + documentComponentId
            + budgetId
            + amount
            + applicableRate
            + onCampusFlag
            + fiscalYear
            + sourceAccount
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
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Unrecovered F And As [GET /research-sys/api/v1/budget-unrecovered-f-and-as/]
	                                          
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
    
            {"columns":["documentComponentId","budgetId","amount","applicableRate","onCampusFlag","fiscalYear","sourceAccount","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"budget:budgetId:documentComponentId"}
		
### Get Blueprint API specification for Budget Unrecovered F And As [GET /research-sys/api/v1/budget-unrecovered-f-and-as/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Unrecovered F And As.md"
            transfer-encoding:chunked


### Update Budget Unrecovered F And As [PUT /research-sys/api/v1/budget-unrecovered-f-and-as/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Unrecovered F And As [PUT /research-sys/api/v1/budget-unrecovered-f-and-as/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Unrecovered F And As [POST /research-sys/api/v1/budget-unrecovered-f-and-as/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Unrecovered F And As [POST /research-sys/api/v1/budget-unrecovered-f-and-as/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"documentComponentId": "(val)","budgetId": "(val)","amount": "(val)","applicableRate": "(val)","onCampusFlag": "(val)","fiscalYear": "(val)","sourceAccount": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Unrecovered F And As by Key [DELETE /research-sys/api/v1/budget-unrecovered-f-and-as/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Unrecovered F And As [DELETE /research-sys/api/v1/budget-unrecovered-f-and-as/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Unrecovered F And As with Matching [DELETE /research-sys/api/v1/budget-unrecovered-f-and-as/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentComponentId
            + budgetId
            + amount
            + applicableRate
            + onCampusFlag
            + fiscalYear
            + sourceAccount
            + hierarchyProposalNumber
            + hiddenInHierarchy

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
