## Proposal Development Budgets [/research-sys/api/v1/proposal-development-budgets/]

### Get Proposal Development Budgets by Key [GET /research-sys/api/v1/proposal-development-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}

### Get All Proposal Development Budgets [GET /research-sys/api/v1/proposal-development-budgets/]
	 
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

### Get All Proposal Development Budgets with Filtering [GET /research-sys/api/v1/proposal-development-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetStatus
            + hierarchyLastSyncHashCode
            + residualFunds
            + endDate
            + modularBudgetFlag
            + documentNumber
            + totalDirectCostLimit
            + budgetAdjustmentDocumentNumber
            + createTimestamp
            + costSharingAmount
            + totalDirectCost
            + ohRateClassCode
            + comments
            + budgetJustification
            + underrecoveryAmount
            + budgetId
            + budgetVersionNumber
            + urRateClassCode
            + parentDocumentTypeCode
            + totalIndirectCost
            + totalCostLimit
            + name
            + onOffCampusFlag
            + submitCostSharingFlag
            + createUser
            + ohRateTypeCode
            + startDate
            + totalCost
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"},
              {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Development Budgets [GET /research-sys/api/v1/proposal-development-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Proposal Development Budgets [GET /research-sys/api/v1/proposal-development-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Development Budgets.md"
            transfer-encoding:chunked


### Update Proposal Development Budgets [PUT /research-sys/api/v1/proposal-development-budgets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Development Budgets [PUT /research-sys/api/v1/proposal-development-budgets/]

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

### Insert Proposal Development Budgets [POST /research-sys/api/v1/proposal-development-budgets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetStatus": "(val)","hierarchyLastSyncHashCode": "(val)","residualFunds": "(val)","endDate": "(val)","modularBudgetFlag": "(val)","documentNumber": "(val)","totalDirectCostLimit": "(val)","budgetAdjustmentDocumentNumber": "(val)","createTimestamp": "(val)","costSharingAmount": "(val)","totalDirectCost": "(val)","ohRateClassCode": "(val)","comments": "(val)","budgetJustification": "(val)","underrecoveryAmount": "(val)","budgetId": "(val)","budgetVersionNumber": "(val)","urRateClassCode": "(val)","parentDocumentTypeCode": "(val)","totalIndirectCost": "(val)","totalCostLimit": "(val)","name": "(val)","onOffCampusFlag": "(val)","submitCostSharingFlag": "(val)","createUser": "(val)","ohRateTypeCode": "(val)","startDate": "(val)","totalCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Development Budgets [POST /research-sys/api/v1/proposal-development-budgets/]

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
            
### Delete Proposal Development Budgets by Key [DELETE /research-sys/api/v1/proposal-development-budgets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Budgets [DELETE /research-sys/api/v1/proposal-development-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Development Budgets with Matching [DELETE /research-sys/api/v1/proposal-development-budgets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetStatus
            + hierarchyLastSyncHashCode
            + residualFunds
            + endDate
            + modularBudgetFlag
            + documentNumber
            + totalDirectCostLimit
            + budgetAdjustmentDocumentNumber
            + createTimestamp
            + costSharingAmount
            + totalDirectCost
            + ohRateClassCode
            + comments
            + budgetJustification
            + underrecoveryAmount
            + budgetId
            + budgetVersionNumber
            + urRateClassCode
            + parentDocumentTypeCode
            + totalIndirectCost
            + totalCostLimit
            + name
            + onOffCampusFlag
            + submitCostSharingFlag
            + createUser
            + ohRateTypeCode
            + startDate
            + totalCost


+ Response 204
