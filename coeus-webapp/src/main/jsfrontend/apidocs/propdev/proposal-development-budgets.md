## Proposal Development Budgets [/propdev/api/v1/proposal-development-budgets/]

### Get Proposal Development Budgets by Key [GET /propdev/api/v1/proposal-development-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}

### Get All Proposal Development Budgets [GET /propdev/api/v1/proposal-development-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Development Budgets with Filtering [GET /propdev/api/v1/proposal-development-budgets/]
    
+ Parameters

    + budgetStatus (optional) - F&A Rate Type. Maximum length is 1.
    + hierarchyLastSyncHashCode (optional) - Hierarchy Last Sync Hash Code.
    + residualFunds (optional) - Residual Funds. Maximum length is 15.
    + endDate (optional) - Project End Date. Maximum length is 21.
    + modularBudgetFlag (optional) - Modular Budget Flag. Maximum length is 1.
    + documentNumber (optional) - Document Number.
    + totalDirectCostLimit (optional) - Total Direct Cost Limit. Maximum length is 15.
    + budgetAdjustmentDocumentNumber (optional) - Budget Adjustment Document Number.
    + createTimestamp (optional) - Create Timestamp.
    + costSharingAmount (optional) - Cost Sharing Amount. Maximum length is 15.
    + totalDirectCost (optional) - Total Direct Cost. Maximum length is 15.
    + ohRateClassCode (optional) - F&A Rate Type. Maximum length is 3.
    + comments (optional) - The Comments for this budget. Maximum length is 2000.
    + budgetJustification (optional) - Budget Justification. Maximum length is 4000.
    + underrecoveryAmount (optional) - Underrecovery Amount. Maximum length is 15.
    + budgetId (optional) - Budget Id.
    + budgetVersionNumber (optional) - Budget Version Number for header display. Maximum length is 3.
    + urRateClassCode (optional) - Ur Rate Class Code. Maximum length is 3.
    + parentDocumentTypeCode (optional) - Parent Document Type Code. Maximum length is 31.
    + totalIndirectCost (optional) - Total Indirect Cost. Maximum length is 15.
    + totalCostLimit (optional) - Total Cost Limit. Maximum length is 15.
    + name (optional) - A free-form text field that describes the purpose or                                         function of the document. Maximum length is 40.
    + onOffCampusFlag (optional) - On Off CampusContractContract flag allowing the user to set all expense line items to be either 'all on' or 'all off-campus'; overriding the object code level defaults. Maximum length is 3.
    + submitCostSharingFlag (optional) - Submit Cost Sharing Flag. Maximum length is 1.
    + createUser (optional) - Create User.
    + ohRateTypeCode (optional) - F&A Rate Type. Maximum length is 3.
    + startDate (optional) - Project Start Date. Maximum length is 21.
    + totalCost (optional) - Total Cost. Maximum length is 15.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Development Budgets [GET /propdev/api/v1/proposal-development-budgets/]
	                                          
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
    
            {"columns":["budgetStatus","hierarchyLastSyncHashCode","residualFunds","endDate","modularBudgetFlag","documentNumber","totalDirectCostLimit","budgetAdjustmentDocumentNumber","createTimestamp","costSharingAmount","totalDirectCost","ohRateClassCode","comments","budgetJustification","underrecoveryAmount","budgetId","budgetVersionNumber","urRateClassCode","parentDocumentTypeCode","totalIndirectCost","totalCostLimit","name","onOffCampusFlag","submitCostSharingFlag","createUser","ohRateTypeCode","startDate","totalCost"],"primaryKey":"budgetId"}
		
### Get Blueprint API specification for Proposal Development Budgets [GET /propdev/api/v1/proposal-development-budgets/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Development Budgets.md"
            transfer-encoding:chunked
### Update Proposal Development Budgets [PUT /propdev/api/v1/proposal-development-budgets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Development Budgets [PUT /propdev/api/v1/proposal-development-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Development Budgets [POST /propdev/api/v1/proposal-development-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Development Budgets [POST /propdev/api/v1/proposal-development-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Development Budgets by Key [DELETE /propdev/api/v1/proposal-development-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Budgets [DELETE /propdev/api/v1/proposal-development-budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Budgets with Matching [DELETE /propdev/api/v1/proposal-development-budgets/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetStatus (optional) - F&A Rate Type. Maximum length is 1.
    + hierarchyLastSyncHashCode (optional) - Hierarchy Last Sync Hash Code.
    + residualFunds (optional) - Residual Funds. Maximum length is 15.
    + endDate (optional) - Project End Date. Maximum length is 21.
    + modularBudgetFlag (optional) - Modular Budget Flag. Maximum length is 1.
    + documentNumber (optional) - Document Number.
    + totalDirectCostLimit (optional) - Total Direct Cost Limit. Maximum length is 15.
    + budgetAdjustmentDocumentNumber (optional) - Budget Adjustment Document Number.
    + createTimestamp (optional) - Create Timestamp.
    + costSharingAmount (optional) - Cost Sharing Amount. Maximum length is 15.
    + totalDirectCost (optional) - Total Direct Cost. Maximum length is 15.
    + ohRateClassCode (optional) - F&A Rate Type. Maximum length is 3.
    + comments (optional) - The Comments for this budget. Maximum length is 2000.
    + budgetJustification (optional) - Budget Justification. Maximum length is 4000.
    + underrecoveryAmount (optional) - Underrecovery Amount. Maximum length is 15.
    + budgetId (optional) - Budget Id.
    + budgetVersionNumber (optional) - Budget Version Number for header display. Maximum length is 3.
    + urRateClassCode (optional) - Ur Rate Class Code. Maximum length is 3.
    + parentDocumentTypeCode (optional) - Parent Document Type Code. Maximum length is 31.
    + totalIndirectCost (optional) - Total Indirect Cost. Maximum length is 15.
    + totalCostLimit (optional) - Total Cost Limit. Maximum length is 15.
    + name (optional) - A free-form text field that describes the purpose or                                         function of the document. Maximum length is 40.
    + onOffCampusFlag (optional) - On Off CampusContractContract flag allowing the user to set all expense line items to be either 'all on' or 'all off-campus'; overriding the object code level defaults. Maximum length is 3.
    + submitCostSharingFlag (optional) - Submit Cost Sharing Flag. Maximum length is 1.
    + createUser (optional) - Create User.
    + ohRateTypeCode (optional) - F&A Rate Type. Maximum length is 3.
    + startDate (optional) - Project Start Date. Maximum length is 21.
    + totalCost (optional) - Total Cost. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
