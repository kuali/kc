## Budgets [/research-sys/api/v1/budgets/]

### Get Budgets by Key [GET /research-sys/api/v1/budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}

### Get All Budgets [GET /research-sys/api/v1/budgets/]
	 
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

### Get All Budgets with Filtering [GET /research-sys/api/v1/budgets/]
    
+ Parameters

        + parentDocumentTypeCode
            + budgetJustification
            + createTimestamp
            + createUser
            + budgetAdjustmentDocumentNumber
            + residualFunds
            + endDate
            + modularBudgetFlag
            + documentNumber
            + totalDirectCostLimit
            + costSharingAmount
            + totalDirectCost
            + ohRateClassCode
            + comments
            + underrecoveryAmount
            + budgetId
            + budgetVersionNumber
            + urRateClassCode
            + totalIndirectCost
            + totalCostLimit
            + name
            + onOffCampusFlag
            + submitCostSharingFlag
            + ohRateTypeCode
            + startDate
            + totalCost

            
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
			
### Get Schema for Budgets [GET /research-sys/api/v1/budgets/]
	                                          
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
		
### Get Blueprint API specification for Budgets [GET /research-sys/api/v1/budgets/]
	 
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


### Update Budgets [PUT /research-sys/api/v1/budgets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budgets [PUT /research-sys/api/v1/budgets/]

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

### Insert Budgets [POST /research-sys/api/v1/budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"parentDocumentTypeCode": "(val)","budgetJustification": "(val)","createTimestamp": "(val)","createUser": "(val)","budgetAdjustmentDocumentNumber": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budgets [POST /research-sys/api/v1/budgets/]

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
            
### Delete Budgets by Key [DELETE /research-sys/api/v1/budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budgets [DELETE /research-sys/api/v1/budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budgets with Matching [DELETE /research-sys/api/v1/budgets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + parentDocumentTypeCode
            + budgetJustification
            + createTimestamp
            + createUser
            + budgetAdjustmentDocumentNumber
            + residualFunds
            + endDate
            + modularBudgetFlag
            + documentNumber
            + totalDirectCostLimit
            + costSharingAmount
            + totalDirectCost
            + ohRateClassCode
            + comments
            + underrecoveryAmount
            + budgetId
            + budgetVersionNumber
            + urRateClassCode
            + totalIndirectCost
            + totalCostLimit
            + name
            + onOffCampusFlag
            + submitCostSharingFlag
            + ohRateTypeCode
            + startDate
            + totalCost

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
