## Budget Line Items [/research-common/api/v1/budget-line-items/]

### Get Budget Line Items by Key [GET /research-common/api/v1/budget-line-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Line Items [GET /research-common/api/v1/budget-line-items/]
	 
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

### Get All Budget Line Items with Filtering [GET /research-common/api/v1/budget-line-items/]
    
+ Parameters

    + costSharingAmount (optional) - Cost Sharing. Maximum length is 15.
    + budgetLineItemId (optional) - Budget Line Item Id.
    + budgetPeriodId (optional) - Budget Period Id. Maximum length is 3.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + applyInRateFlag (optional) - Apply Inflation?. Maximum length is 1.
    + basedOnLineItem (optional) - Based On Line Item. Maximum length is 3.
    + budgetCategoryCode (optional) - Budget Category. Maximum length is 3.
    + budgetJustification (optional) - Budget Justification Notes. Maximum length is 200.
    + costElement (optional) - Object Code Name. Maximum length is 8.
    + groupName (optional) - Group. Maximum length is 80.
    + endDate (optional) - End Date. Maximum length is 21.
    + lineItemCost (optional) - Total Base Cost. Maximum length is 15.
    + lineItemDescription (optional) - Description. Maximum length is 80.
    + lineItemSequence (optional) - Line Item Sequence. Maximum length is 3.
    + onOffCampusFlag (optional) - On CampusContract?. Maximum length is 1.
    + quantity (optional) - Quantity. Maximum length is 4.
    + startDate (optional) - Start Date. Maximum length is 21.
    + underrecoveryAmount (optional) - Unrecovered F&A. Maximum length is 15.
    + submitCostSharingFlag (optional) - Submit Cost Sharing Flag. Maximum length is 1.
    + formulatedCostElementFlag (optional) - Formulated Cost Element Flag.
    + subAwardNumber (optional) - Sub Award Number.
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
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Line Items [GET /research-common/api/v1/budget-line-items/]
	                                          
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
    
            {"columns":["costSharingAmount","budgetLineItemId","budgetPeriodId","lineItemNumber","budgetId","budgetPeriod","applyInRateFlag","basedOnLineItem","budgetCategoryCode","budgetJustification","costElement","groupName","endDate","lineItemCost","lineItemDescription","lineItemSequence","onOffCampusFlag","quantity","startDate","underrecoveryAmount","submitCostSharingFlag","formulatedCostElementFlag","subAwardNumber","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"budgetLineItemId"}
		
### Get Blueprint API specification for Budget Line Items [GET /research-common/api/v1/budget-line-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Line Items.md"
            transfer-encoding:chunked


### Update Budget Line Items [PUT /research-common/api/v1/budget-line-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Line Items [PUT /research-common/api/v1/budget-line-items/]

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

### Insert Budget Line Items [POST /research-common/api/v1/budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costSharingAmount": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","applyInRateFlag": "(val)","basedOnLineItem": "(val)","budgetCategoryCode": "(val)","budgetJustification": "(val)","costElement": "(val)","groupName": "(val)","endDate": "(val)","lineItemCost": "(val)","lineItemDescription": "(val)","lineItemSequence": "(val)","onOffCampusFlag": "(val)","quantity": "(val)","startDate": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","formulatedCostElementFlag": "(val)","subAwardNumber": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Line Items [POST /research-common/api/v1/budget-line-items/]

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
            
### Delete Budget Line Items by Key [DELETE /research-common/api/v1/budget-line-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Line Items [DELETE /research-common/api/v1/budget-line-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Line Items with Matching [DELETE /research-common/api/v1/budget-line-items/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + costSharingAmount (optional) - Cost Sharing. Maximum length is 15.
    + budgetLineItemId (optional) - Budget Line Item Id.
    + budgetPeriodId (optional) - Budget Period Id. Maximum length is 3.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + applyInRateFlag (optional) - Apply Inflation?. Maximum length is 1.
    + basedOnLineItem (optional) - Based On Line Item. Maximum length is 3.
    + budgetCategoryCode (optional) - Budget Category. Maximum length is 3.
    + budgetJustification (optional) - Budget Justification Notes. Maximum length is 200.
    + costElement (optional) - Object Code Name. Maximum length is 8.
    + groupName (optional) - Group. Maximum length is 80.
    + endDate (optional) - End Date. Maximum length is 21.
    + lineItemCost (optional) - Total Base Cost. Maximum length is 15.
    + lineItemDescription (optional) - Description. Maximum length is 80.
    + lineItemSequence (optional) - Line Item Sequence. Maximum length is 3.
    + onOffCampusFlag (optional) - On CampusContract?. Maximum length is 1.
    + quantity (optional) - Quantity. Maximum length is 4.
    + startDate (optional) - Start Date. Maximum length is 21.
    + underrecoveryAmount (optional) - Unrecovered F&A. Maximum length is 15.
    + submitCostSharingFlag (optional) - Submit Cost Sharing Flag. Maximum length is 1.
    + formulatedCostElementFlag (optional) - Formulated Cost Element Flag.
    + subAwardNumber (optional) - Sub Award Number.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
