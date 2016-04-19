## Budget Rate And Bases [/research-common/api/v1/budget-rate-and-bases/]

### Get Budget Rate And Bases by Key [GET /research-common/api/v1/budget-rate-and-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Budget Rate And Bases [GET /research-common/api/v1/budget-rate-and-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Rate And Bases with Filtering [GET /research-common/api/v1/budget-rate-and-bases/]
    
+ Parameters

    + baseCost (optional) - Base Cost. Maximum length is 15.
    + budgetRateAndBaseId (optional) - Budget Rate And Base Id.
    + budgetLineItemCalculatedAmountId (optional) - Budget Line Item Calculated Amount Id.
    + budgetLineItemId (optional) - Budget Line Item Id.
    + baseCostSharing (optional) - Base Cost Sharing. Maximum length is 15.
    + budgetPeriodId (optional) - Budget Period Id.
    + endDate (optional) - End Date. Maximum length is 21.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + rateNumber (optional) - Rate Number. Maximum length is 3.
    + appliedRate (optional) - Applied Rate. Maximum length is 7.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + calculatedCost (optional) - Calculated Cost. Maximum length is 15.
    + onOffCampusFlag (optional) - On CampusContract?. Maximum length is 1.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + calculatedCostSharing (optional) - Calculated Cost Sharing. Maximum length is 15.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 21.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Rate And Bases [GET /research-common/api/v1/budget-rate-and-bases/]
	                                          
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
    
            {"columns":["baseCost","budgetRateAndBaseId","budgetLineItemCalculatedAmountId","budgetLineItemId","baseCostSharing","budgetPeriodId","endDate","rateTypeCode","budgetId","rateNumber","appliedRate","budgetPeriod","calculatedCost","onOffCampusFlag","rateClassCode","calculatedCostSharing","lineItemNumber","startDate"],"primaryKey":"budgetRateAndBaseId"}
		
### Get Blueprint API specification for Budget Rate And Bases [GET /research-common/api/v1/budget-rate-and-bases/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Rate And Bases.md"
            transfer-encoding:chunked


### Update Budget Rate And Bases [PUT /research-common/api/v1/budget-rate-and-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Rate And Bases [PUT /research-common/api/v1/budget-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Rate And Bases [POST /research-common/api/v1/budget-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Rate And Bases [POST /research-common/api/v1/budget-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Rate And Bases by Key [DELETE /research-common/api/v1/budget-rate-and-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Rate And Bases [DELETE /research-common/api/v1/budget-rate-and-bases/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Rate And Bases with Matching [DELETE /research-common/api/v1/budget-rate-and-bases/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + baseCost (optional) - Base Cost. Maximum length is 15.
    + budgetRateAndBaseId (optional) - Budget Rate And Base Id.
    + budgetLineItemCalculatedAmountId (optional) - Budget Line Item Calculated Amount Id.
    + budgetLineItemId (optional) - Budget Line Item Id.
    + baseCostSharing (optional) - Base Cost Sharing. Maximum length is 15.
    + budgetPeriodId (optional) - Budget Period Id.
    + endDate (optional) - End Date. Maximum length is 21.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + budgetId (optional) - Budget Id.
    + rateNumber (optional) - Rate Number. Maximum length is 3.
    + appliedRate (optional) - Applied Rate. Maximum length is 7.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + calculatedCost (optional) - Calculated Cost. Maximum length is 15.
    + onOffCampusFlag (optional) - On CampusContract?. Maximum length is 1.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + calculatedCostSharing (optional) - Calculated Cost Sharing. Maximum length is 15.
    + lineItemNumber (optional) - Line Item Number. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 21.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
