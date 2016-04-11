## Budget Personnel Calculated Amounts [/research-sys/api/v1/budget-personnel-calculated-amounts/]

### Get Budget Personnel Calculated Amounts by Key [GET /research-sys/api/v1/budget-personnel-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}

### Get All Budget Personnel Calculated Amounts [GET /research-sys/api/v1/budget-personnel-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Personnel Calculated Amounts with Filtering [GET /research-sys/api/v1/budget-personnel-calculated-amounts/]
    
+ Parameters

        + personNumber
            + budgetPersonnelCalculatedAmountId
            + budgetPersonnelLineItemId
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

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Personnel Calculated Amounts [GET /research-sys/api/v1/budget-personnel-calculated-amounts/]
	                                          
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
    
            {"columns":["personNumber","budgetPersonnelCalculatedAmountId","budgetPersonnelLineItemId","budgetPeriodId","rateTypeDescription","applyRateFlag","rateTypeCode","budgetId","budgetPeriod","calculatedCost","rateClassCode","calculatedCostSharing","lineItemNumber"],"primaryKey":"budgetPersonnelCalculatedAmountId"}
		
### Get Blueprint API specification for Budget Personnel Calculated Amounts [GET /research-sys/api/v1/budget-personnel-calculated-amounts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Personnel Calculated Amounts.md"
            transfer-encoding:chunked


### Update Budget Personnel Calculated Amounts [PUT /research-sys/api/v1/budget-personnel-calculated-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Personnel Calculated Amounts [PUT /research-sys/api/v1/budget-personnel-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Personnel Calculated Amounts [POST /research-sys/api/v1/budget-personnel-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Personnel Calculated Amounts [POST /research-sys/api/v1/budget-personnel-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Personnel Calculated Amounts by Key [DELETE /research-sys/api/v1/budget-personnel-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Calculated Amounts [DELETE /research-sys/api/v1/budget-personnel-calculated-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Calculated Amounts with Matching [DELETE /research-sys/api/v1/budget-personnel-calculated-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + personNumber
            + budgetPersonnelCalculatedAmountId
            + budgetPersonnelLineItemId
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

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
