## Budget Rate And Bases [/research-sys/api/v1/budget-rate-and-bases/]

### Get Budget Rate And Bases by Key [GET /research-sys/api/v1/budget-rate-and-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Budget Rate And Bases [GET /research-sys/api/v1/budget-rate-and-bases/]
	 
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

### Get All Budget Rate And Bases with Filtering [GET /research-sys/api/v1/budget-rate-and-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + baseCost
            + budgetRateAndBaseId
            + budgetLineItemCalculatedAmountId
            + budgetLineItemId
            + baseCostSharing
            + budgetPeriodId
            + endDate
            + rateTypeCode
            + budgetId
            + rateNumber
            + appliedRate
            + budgetPeriod
            + calculatedCost
            + onOffCampusFlag
            + rateClassCode
            + calculatedCostSharing
            + lineItemNumber
            + startDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Rate And Bases [GET /research-sys/api/v1/budget-rate-and-bases/]
	 
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
		
### Get Blueprint API specification for Budget Rate And Bases [GET /research-sys/api/v1/budget-rate-and-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Rate And Bases.md"
            transfer-encoding:chunked


### Update Budget Rate And Bases [PUT /research-sys/api/v1/budget-rate-and-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Rate And Bases [PUT /research-sys/api/v1/budget-rate-and-bases/]

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

### Insert Budget Rate And Bases [POST /research-sys/api/v1/budget-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"baseCost": "(val)","budgetRateAndBaseId": "(val)","budgetLineItemCalculatedAmountId": "(val)","budgetLineItemId": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Rate And Bases [POST /research-sys/api/v1/budget-rate-and-bases/]

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
            
### Delete Budget Rate And Bases by Key [DELETE /research-sys/api/v1/budget-rate-and-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Rate And Bases [DELETE /research-sys/api/v1/budget-rate-and-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Rate And Bases with Matching [DELETE /research-sys/api/v1/budget-rate-and-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + baseCost
            + budgetRateAndBaseId
            + budgetLineItemCalculatedAmountId
            + budgetLineItemId
            + baseCostSharing
            + budgetPeriodId
            + endDate
            + rateTypeCode
            + budgetId
            + rateNumber
            + appliedRate
            + budgetPeriod
            + calculatedCost
            + onOffCampusFlag
            + rateClassCode
            + calculatedCostSharing
            + lineItemNumber
            + startDate


+ Response 204
