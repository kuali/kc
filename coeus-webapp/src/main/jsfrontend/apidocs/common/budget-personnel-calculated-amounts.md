## Budget Personnel Calculated Amounts [/research-common/api/v1/budget-personnel-calculated-amounts/]

### Get Budget Personnel Calculated Amounts by Key [GET /research-common/api/v1/budget-personnel-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}

### Get All Budget Personnel Calculated Amounts [GET /research-common/api/v1/budget-personnel-calculated-amounts/]
	 
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

### Get All Budget Personnel Calculated Amounts with Filtering [GET /research-common/api/v1/budget-personnel-calculated-amounts/]
    
+ Parameters

    + personNumber (optional) - Person Number. Maximum length is 3.
    + budgetPersonnelCalculatedAmountId (optional) - Budget Personnel Calculated Amount Id.
    + budgetPersonnelLineItemId (optional) - Budget Personnel Line Item Id.
    + budgetPeriodId (optional) - Budget Period Id.
    + rateTypeDescription (optional) - Rate Type Description.
    + applyRateFlag (optional) - Apply Rate?. Maximum length is 1.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + calculatedCost (optional) - Calculated Cost. Maximum length is 15.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + calculatedCostSharing (optional) - Calculated Cost Sharing. Maximum length is 15.
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
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"},
              {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Personnel Calculated Amounts [GET /research-common/api/v1/budget-personnel-calculated-amounts/]
	                                          
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
		
### Get Blueprint API specification for Budget Personnel Calculated Amounts [GET /research-common/api/v1/budget-personnel-calculated-amounts/]
	 
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


### Update Budget Personnel Calculated Amounts [PUT /research-common/api/v1/budget-personnel-calculated-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Personnel Calculated Amounts [PUT /research-common/api/v1/budget-personnel-calculated-amounts/]

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

### Insert Budget Personnel Calculated Amounts [POST /research-common/api/v1/budget-personnel-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personNumber": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","budgetPeriodId": "(val)","rateTypeDescription": "(val)","applyRateFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Personnel Calculated Amounts [POST /research-common/api/v1/budget-personnel-calculated-amounts/]

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
            
### Delete Budget Personnel Calculated Amounts by Key [DELETE /research-common/api/v1/budget-personnel-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Calculated Amounts [DELETE /research-common/api/v1/budget-personnel-calculated-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Calculated Amounts with Matching [DELETE /research-common/api/v1/budget-personnel-calculated-amounts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personNumber (optional) - Person Number. Maximum length is 3.
    + budgetPersonnelCalculatedAmountId (optional) - Budget Personnel Calculated Amount Id.
    + budgetPersonnelLineItemId (optional) - Budget Personnel Line Item Id.
    + budgetPeriodId (optional) - Budget Period Id.
    + rateTypeDescription (optional) - Rate Type Description.
    + applyRateFlag (optional) - Apply Rate?. Maximum length is 1.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + calculatedCost (optional) - Calculated Cost. Maximum length is 15.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + calculatedCostSharing (optional) - Calculated Cost Sharing. Maximum length is 15.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
