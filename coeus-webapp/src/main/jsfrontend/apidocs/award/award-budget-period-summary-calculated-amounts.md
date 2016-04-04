## Award Budget Period Summary Calculated Amounts [/research-sys/api/v1/award-budget-period-summary-calculated-amounts/]

### Get Award Budget Period Summary Calculated Amounts by Key [GET /research-sys/api/v1/award-budget-period-summary-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Period Summary Calculated Amounts [GET /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Period Summary Calculated Amounts with Filtering [GET /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardBudgetPeriodSummaryCalculatedAmountId
            + budgetPeriodId
            + costElement
            + onOffCampusFlag
            + rateClassType
            + calculatedCost
            + calculatedCostSharing
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Period Summary Calculated Amounts [GET /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]
	 
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
		
### Get Blueprint API specification for Award Budget Period Summary Calculated Amounts [GET /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Period Summary Calculated Amounts.md"
            transfer-encoding:chunked


### Update Award Budget Period Summary Calculated Amounts [PUT /research-sys/api/v1/award-budget-period-summary-calculated-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Period Summary Calculated Amounts [PUT /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Period Summary Calculated Amounts [POST /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Period Summary Calculated Amounts [POST /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Period Summary Calculated Amounts by Key [DELETE /research-sys/api/v1/award-budget-period-summary-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Period Summary Calculated Amounts [DELETE /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Budget Period Summary Calculated Amounts with Matching [DELETE /research-sys/api/v1/award-budget-period-summary-calculated-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardBudgetPeriodSummaryCalculatedAmountId
            + budgetPeriodId
            + costElement
            + onOffCampusFlag
            + rateClassType
            + calculatedCost
            + calculatedCostSharing


+ Response 204
