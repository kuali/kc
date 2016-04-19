## Budgets [/research-common/api/v1/budgets/]

### Get Budgets by Key [GET /research-common/api/v1/budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}

### Get All Budgets [GET /research-common/api/v1/budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budgets with Filtering [GET /research-common/api/v1/budgets/]
    
+ Parameters

    + parentDocumentTypeCode (optional) - Parent Document Type Code. Maximum length is 31.
    + budgetJustification (optional) - Budget Justification. Maximum length is 4000.
    + createTimestamp (optional) - Create Timestamp.
    + createUser (optional) - Create User.
    + budgetAdjustmentDocumentNumber (optional) - Budget Adjustment Document Number.
    + residualFunds (optional) - Residual Funds. Maximum length is 15.
    + endDate (optional) - Project End Date. Maximum length is 21.
    + modularBudgetFlag (optional) - Modular Budget Flag. Maximum length is 1.
    + documentNumber (optional) - Document Number.
    + totalDirectCostLimit (optional) - Total Direct Cost Limit. Maximum length is 15.
    + costSharingAmount (optional) - Cost Sharing Amount. Maximum length is 15.
    + totalDirectCost (optional) - Total Direct Cost. Maximum length is 15.
    + ohRateClassCode (optional) - F&A Rate Type. Maximum length is 3.
    + comments (optional) - The Comments for this budget. Maximum length is 2000.
    + underrecoveryAmount (optional) - Underrecovery Amount. Maximum length is 15.
    + budgetId (optional) - Budget Id.
    + budgetVersionNumber (optional) - Budget Version Number for header display. Maximum length is 3.
    + urRateClassCode (optional) - Ur Rate Class Code. Maximum length is 3.
    + totalIndirectCost (optional) - Total Indirect Cost. Maximum length is 15.
    + totalCostLimit (optional) - Total Cost Limit. Maximum length is 15.
    + name (optional) - A free-form text field that describes the purpose or                                         function of the document. Maximum length is 40.
    + onOffCampusFlag (optional) - On Off CampusContractContract flag allowing the user to set all expense line items to be either 'all on' or 'all off-campus'; overriding the object code level defaults. Maximum length is 3.
    + submitCostSharingFlag (optional) - Submit Cost Sharing Flag. Maximum length is 1.
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
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budgets [GET /research-common/api/v1/budgets/]
	                                          
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
    
            {"columns":["parentDocumentTypeCode","budgetJustification","createTimestamp","createUser","budgetAdjustmentDocumentNumber","residualFunds","endDate","modularBudgetFlag","documentNumber","totalDirectCostLimit","costSharingAmount","totalDirectCost","ohRateClassCode","comments","underrecoveryAmount","budgetId","budgetVersionNumber","urRateClassCode","totalIndirectCost","totalCostLimit","name","onOffCampusFlag","submitCostSharingFlag","ohRateTypeCode","startDate","totalCost"],"primaryKey":"budgetId"}
		
### Get Blueprint API specification for Budgets [GET /research-common/api/v1/budgets/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budgets.md"
            transfer-encoding:chunked


### Update Budgets [PUT /research-common/api/v1/budgets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budgets [PUT /research-common/api/v1/budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budgets [POST /research-common/api/v1/budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budgets [POST /research-common/api/v1/budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budgets by Key [DELETE /research-common/api/v1/budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budgets [DELETE /research-common/api/v1/budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budgets with Matching [DELETE /research-common/api/v1/budgets/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + parentDocumentTypeCode (optional) - Parent Document Type Code. Maximum length is 31.
    + budgetJustification (optional) - Budget Justification. Maximum length is 4000.
    + createTimestamp (optional) - Create Timestamp.
    + createUser (optional) - Create User.
    + budgetAdjustmentDocumentNumber (optional) - Budget Adjustment Document Number.
    + residualFunds (optional) - Residual Funds. Maximum length is 15.
    + endDate (optional) - Project End Date. Maximum length is 21.
    + modularBudgetFlag (optional) - Modular Budget Flag. Maximum length is 1.
    + documentNumber (optional) - Document Number.
    + totalDirectCostLimit (optional) - Total Direct Cost Limit. Maximum length is 15.
    + costSharingAmount (optional) - Cost Sharing Amount. Maximum length is 15.
    + totalDirectCost (optional) - Total Direct Cost. Maximum length is 15.
    + ohRateClassCode (optional) - F&A Rate Type. Maximum length is 3.
    + comments (optional) - The Comments for this budget. Maximum length is 2000.
    + underrecoveryAmount (optional) - Underrecovery Amount. Maximum length is 15.
    + budgetId (optional) - Budget Id.
    + budgetVersionNumber (optional) - Budget Version Number for header display. Maximum length is 3.
    + urRateClassCode (optional) - Ur Rate Class Code. Maximum length is 3.
    + totalIndirectCost (optional) - Total Indirect Cost. Maximum length is 15.
    + totalCostLimit (optional) - Total Cost Limit. Maximum length is 15.
    + name (optional) - A free-form text field that describes the purpose or                                         function of the document. Maximum length is 40.
    + onOffCampusFlag (optional) - On Off CampusContractContract flag allowing the user to set all expense line items to be either 'all on' or 'all off-campus'; overriding the object code level defaults. Maximum length is 3.
    + submitCostSharingFlag (optional) - Submit Cost Sharing Flag. Maximum length is 1.
    + ohRateTypeCode (optional) - F&A Rate Type. Maximum length is 3.
    + startDate (optional) - Project Start Date. Maximum length is 21.
    + totalCost (optional) - Total Cost. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
