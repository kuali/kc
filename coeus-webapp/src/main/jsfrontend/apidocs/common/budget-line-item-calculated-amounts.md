## Budget Line Item Calculated Amounts [/research-sys/api/v1/budget-line-item-calculated-amounts/]

### Get Budget Line Item Calculated Amounts by Key [GET /research-sys/api/v1/budget-line-item-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}

### Get All Budget Line Item Calculated Amounts [GET /research-sys/api/v1/budget-line-item-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Line Item Calculated Amounts with Filtering [GET /research-sys/api/v1/budget-line-item-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetLineItemCalculatedAmountId
            + budgetLineItemId
            + budgetPeriodId
            + rateTypeDescription
            + applyRateFlag
            + rateTypeCode
            + budgetId
            + budgetPeriod
            + calculatedCost
            + rateClassCode
            + calculatedCostSharing
            + lineItemNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Line Item Calculated Amounts [GET /research-sys/api/v1/budget-line-item-calculated-amounts/]
	 
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
		
### Get Blueprint API specification for Budget Line Item Calculated Amounts [GET /research-sys/api/v1/budget-line-item-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Line Item Calculated Amounts.md"
            transfer-encoding:chunked


### Update Budget Line Item Calculated Amounts [PUT /research-sys/api/v1/budget-line-item-calculated-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Line Item Calculated Amounts [PUT /research-sys/api/v1/budget-line-item-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Line Item Calculated Amounts [POST /research-sys/api/v1/budget-line-item-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Line Item Calculated Amounts [POST /research-sys/api/v1/budget-line-item-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Line Item Calculated Amounts by Key [DELETE /research-sys/api/v1/budget-line-item-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Line Item Calculated Amounts [DELETE /research-sys/api/v1/budget-line-item-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Line Item Calculated Amounts with Matching [DELETE /research-sys/api/v1/budget-line-item-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetLineItemCalculatedAmountId
            + budgetLineItemId
            + budgetPeriodId
            + rateTypeDescription
            + applyRateFlag
            + rateTypeCode
            + budgetId
            + budgetPeriod
            + calculatedCost
            + rateClassCode
            + calculatedCostSharing
            + lineItemNumber


+ Response 204
