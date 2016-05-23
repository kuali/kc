## Proposal Development Budget Overviews [/propdev/api/v1/proposal-development-budget-overviews/]

### Get Proposal Development Budget Overviews by Key [GET /propdev/api/v1/proposal-development-budget-overviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}

### Get All Proposal Development Budget Overviews [GET /propdev/api/v1/proposal-development-budget-overviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Development Budget Overviews with Filtering [GET /propdev/api/v1/proposal-development-budget-overviews/]
    
+ Parameters

    + budgetId (optional) - 
    + documentNumber (optional) - 
    + budgetVersionNumber (optional) - 
    + costSharingAmount (optional) - 
    + endDate (optional) - 
    + modularBudgetFlag (optional) - 
    + ohRateTypeCode (optional) - 
    + ohRateClassCode (optional) - 
    + residualFunds (optional) - 
    + startDate (optional) - 
    + totalCost (optional) - 
    + totalCostLimit (optional) - 
    + totalDirectCostLimit (optional) - 
    + totalDirectCost (optional) - 
    + totalIndirectCost (optional) - 
    + underrecoveryAmount (optional) - 
    + comments (optional) - 
    + onOffCampusFlag (optional) - 
    + urRateClassCode (optional) - 
    + submitCostSharingFlag (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Development Budget Overviews [GET /propdev/api/v1/proposal-development-budget-overviews/]
	                                          
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
    
            {"columns":["budgetId","documentNumber","budgetVersionNumber","costSharingAmount","endDate","modularBudgetFlag","ohRateTypeCode","ohRateClassCode","residualFunds","startDate","totalCost","totalCostLimit","totalDirectCostLimit","totalDirectCost","totalIndirectCost","underrecoveryAmount","comments","onOffCampusFlag","urRateClassCode","submitCostSharingFlag"],"primaryKey":"budgetId"}
		
### Get Blueprint API specification for Proposal Development Budget Overviews [GET /propdev/api/v1/proposal-development-budget-overviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Development Budget Overviews.md"
            transfer-encoding:chunked
### Update Proposal Development Budget Overviews [PUT /propdev/api/v1/proposal-development-budget-overviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Development Budget Overviews [PUT /propdev/api/v1/proposal-development-budget-overviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Development Budget Overviews [POST /propdev/api/v1/proposal-development-budget-overviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Development Budget Overviews [POST /propdev/api/v1/proposal-development-budget-overviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","documentNumber": "(val)","budgetVersionNumber": "(val)","costSharingAmount": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","ohRateTypeCode": "(val)","ohRateClassCode": "(val)","residualFunds": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","comments": "(val)","onOffCampusFlag": "(val)","urRateClassCode": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Development Budget Overviews by Key [DELETE /propdev/api/v1/proposal-development-budget-overviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Budget Overviews [DELETE /propdev/api/v1/proposal-development-budget-overviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Budget Overviews with Matching [DELETE /propdev/api/v1/proposal-development-budget-overviews/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetId (optional) - 
    + documentNumber (optional) - 
    + budgetVersionNumber (optional) - 
    + costSharingAmount (optional) - 
    + endDate (optional) - 
    + modularBudgetFlag (optional) - 
    + ohRateTypeCode (optional) - 
    + ohRateClassCode (optional) - 
    + residualFunds (optional) - 
    + startDate (optional) - 
    + totalCost (optional) - 
    + totalCostLimit (optional) - 
    + totalDirectCostLimit (optional) - 
    + totalDirectCost (optional) - 
    + totalIndirectCost (optional) - 
    + underrecoveryAmount (optional) - 
    + comments (optional) - 
    + onOffCampusFlag (optional) - 
    + urRateClassCode (optional) - 
    + submitCostSharingFlag (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
