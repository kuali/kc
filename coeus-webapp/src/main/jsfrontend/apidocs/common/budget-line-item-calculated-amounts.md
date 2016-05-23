## Budget Line Item Calculated Amounts [/research-common/api/v1/budget-line-item-calculated-amounts/]

### Get Budget Line Item Calculated Amounts by Key [GET /research-common/api/v1/budget-line-item-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}

### Get All Budget Line Item Calculated Amounts [GET /research-common/api/v1/budget-line-item-calculated-amounts/]
	 
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

### Get All Budget Line Item Calculated Amounts with Filtering [GET /research-common/api/v1/budget-line-item-calculated-amounts/]
    
+ Parameters

    + budgetLineItemCalculatedAmountId (optional) - Budget Line Item Calculated Amount Id.
    + budgetLineItemId (optional) - Budget Line Item Id.
    + budgetPeriodId (optional) - Budget Period Id.
    + rateTypeDescription (optional) - Rate Type Description.
    + applyRateFlag (optional) - Apply Rate?. Maximum length is 1.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + calculatedCost (optional) - Rate Cost. Maximum length is 15.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + calculatedCostSharing (optional) - Rate Cost Sharing. Maximum length is 15.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.

            
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
			
### Get Schema for Budget Line Item Calculated Amounts [GET /research-common/api/v1/budget-line-item-calculated-amounts/]
	                                          
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
    
            {"columns":["budgetLineItemCalculatedAmountId","budgetLineItemId","budgetPeriodId","rateTypeDescription","applyRateFlag","rateTypeCode","budgetId","budgetPeriod","calculatedCost","rateClassCode","calculatedCostSharing","lineItemNumber"],"primaryKey":"budgetLineItemCalculatedAmountId"}
		
### Get Blueprint API specification for Budget Line Item Calculated Amounts [GET /research-common/api/v1/budget-line-item-calculated-amounts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Line Item Calculated Amounts.md"
            transfer-encoding:chunked
### Update Budget Line Item Calculated Amounts [PUT /research-common/api/v1/budget-line-item-calculated-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Line Item Calculated Amounts [PUT /research-common/api/v1/budget-line-item-calculated-amounts/]

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
### Insert Budget Line Item Calculated Amounts [POST /research-common/api/v1/budget-line-item-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Line Item Calculated Amounts [POST /research-common/api/v1/budget-line-item-calculated-amounts/]

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
### Delete Budget Line Item Calculated Amounts by Key [DELETE /research-common/api/v1/budget-line-item-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Line Item Calculated Amounts [DELETE /research-common/api/v1/budget-line-item-calculated-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Line Item Calculated Amounts with Matching [DELETE /research-common/api/v1/budget-line-item-calculated-amounts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetLineItemCalculatedAmountId (optional) - Budget Line Item Calculated Amount Id.
    + budgetLineItemId (optional) - Budget Line Item Id.
    + budgetPeriodId (optional) - Budget Period Id.
    + rateTypeDescription (optional) - Rate Type Description.
    + applyRateFlag (optional) - Apply Rate?. Maximum length is 1.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + calculatedCost (optional) - Rate Cost. Maximum length is 15.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + calculatedCostSharing (optional) - Rate Cost Sharing. Maximum length is 15.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
