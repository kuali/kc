## Budget Line Items [/research-sys/api/v1/budget-line-items/]

### Get Budget Line Items by Key [GET /research-sys/api/v1/budget-line-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Line Items [GET /research-sys/api/v1/budget-line-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Line Items with Filtering [GET /research-sys/api/v1/budget-line-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + costSharingAmount
            + budgetLineItemId
            + budgetPeriodId
            + lineItemNumber
            + budgetId
            + budgetPeriod
            + applyInRateFlag
            + basedOnLineItem
            + budgetCategoryCode
            + budgetJustification
            + costElement
            + groupName
            + endDate
            + lineItemCost
            + lineItemDescription
            + lineItemSequence
            + onOffCampusFlag
            + quantity
            + startDate
            + underrecoveryAmount
            + submitCostSharingFlag
            + formulatedCostElementFlag
            + subAwardNumber
            + hierarchyProposalNumber
            + hiddenInHierarchy
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Line Items [GET /research-sys/api/v1/budget-line-items/]
	 
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
		
### Get Blueprint API specification for Budget Line Items [GET /research-sys/api/v1/budget-line-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Line Items.md"
            transfer-encoding:chunked


### Update Budget Line Items [PUT /research-sys/api/v1/budget-line-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Line Items [PUT /research-sys/api/v1/budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Line Items [POST /research-sys/api/v1/budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Line Items [POST /research-sys/api/v1/budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Line Items by Key [DELETE /research-sys/api/v1/budget-line-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Line Items [DELETE /research-sys/api/v1/budget-line-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Line Items with Matching [DELETE /research-sys/api/v1/budget-line-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + costSharingAmount
            + budgetLineItemId
            + budgetPeriodId
            + lineItemNumber
            + budgetId
            + budgetPeriod
            + applyInRateFlag
            + basedOnLineItem
            + budgetCategoryCode
            + budgetJustification
            + costElement
            + groupName
            + endDate
            + lineItemCost
            + lineItemDescription
            + lineItemSequence
            + onOffCampusFlag
            + quantity
            + startDate
            + underrecoveryAmount
            + submitCostSharingFlag
            + formulatedCostElementFlag
            + subAwardNumber
            + hierarchyProposalNumber
            + hiddenInHierarchy


+ Response 204
