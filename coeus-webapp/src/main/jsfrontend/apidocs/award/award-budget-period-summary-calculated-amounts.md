## Award Budget Period Summary Calculated Amounts [/award/api/v1/award-budget-period-summary-calculated-amounts/]

### Get Award Budget Period Summary Calculated Amounts by Key [GET /award/api/v1/award-budget-period-summary-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Period Summary Calculated Amounts [GET /award/api/v1/award-budget-period-summary-calculated-amounts/]
	 
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

### Get All Award Budget Period Summary Calculated Amounts with Filtering [GET /award/api/v1/award-budget-period-summary-calculated-amounts/]
    
+ Parameters

    + awardBudgetPeriodSummaryCalculatedAmountId (optional) - Awd Bgt Per Sum Calc Amt Id. Maximum length is 22.
    + budgetPeriodId (optional) - Budget Period Id. Maximum length is 22.
    + costElement (optional) - Cost Element. Maximum length is 8.
    + onOffCampusFlag (optional) - On Off CampusContractContract Flag. Maximum length is 1.
    + rateClassType (optional) - Rate Class Type. Maximum length is 1.
    + calculatedCost (optional) - Calculated Cost. Maximum length is 22.
    + calculatedCostSharing (optional) - Calculated Cost Sharing. Maximum length is 22.

            
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
			
### Get Schema for Award Budget Period Summary Calculated Amounts [GET /award/api/v1/award-budget-period-summary-calculated-amounts/]
	                                          
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
    
            {"columns":["awardBudgetPeriodSummaryCalculatedAmountId","budgetPeriodId","costElement","onOffCampusFlag","rateClassType","calculatedCost","calculatedCostSharing"],"primaryKey":"awardBudgetPeriodSummaryCalculatedAmountId"}
		
### Get Blueprint API specification for Award Budget Period Summary Calculated Amounts [GET /award/api/v1/award-budget-period-summary-calculated-amounts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Period Summary Calculated Amounts.md"
            transfer-encoding:chunked


### Update Award Budget Period Summary Calculated Amounts [PUT /award/api/v1/award-budget-period-summary-calculated-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Period Summary Calculated Amounts [PUT /award/api/v1/award-budget-period-summary-calculated-amounts/]

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

### Insert Award Budget Period Summary Calculated Amounts [POST /award/api/v1/award-budget-period-summary-calculated-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardBudgetPeriodSummaryCalculatedAmountId": "(val)","budgetPeriodId": "(val)","costElement": "(val)","onOffCampusFlag": "(val)","rateClassType": "(val)","calculatedCost": "(val)","calculatedCostSharing": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Period Summary Calculated Amounts [POST /award/api/v1/award-budget-period-summary-calculated-amounts/]

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
            
### Delete Award Budget Period Summary Calculated Amounts by Key [DELETE /award/api/v1/award-budget-period-summary-calculated-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Period Summary Calculated Amounts [DELETE /award/api/v1/award-budget-period-summary-calculated-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Period Summary Calculated Amounts with Matching [DELETE /award/api/v1/award-budget-period-summary-calculated-amounts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardBudgetPeriodSummaryCalculatedAmountId (optional) - Awd Bgt Per Sum Calc Amt Id. Maximum length is 22.
    + budgetPeriodId (optional) - Budget Period Id. Maximum length is 22.
    + costElement (optional) - Cost Element. Maximum length is 8.
    + onOffCampusFlag (optional) - On Off CampusContractContract Flag. Maximum length is 1.
    + rateClassType (optional) - Rate Class Type. Maximum length is 1.
    + calculatedCost (optional) - Calculated Cost. Maximum length is 22.
    + calculatedCostSharing (optional) - Calculated Cost Sharing. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
