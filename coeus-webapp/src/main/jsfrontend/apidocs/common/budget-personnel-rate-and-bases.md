## Budget Personnel Rate And Bases [/research-common/api/v1/budget-personnel-rate-and-bases/]

### Get Budget Personnel Rate And Bases by Key [GET /research-common/api/v1/budget-personnel-rate-and-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Budget Personnel Rate And Bases [GET /research-common/api/v1/budget-personnel-rate-and-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Personnel Rate And Bases with Filtering [GET /research-common/api/v1/budget-personnel-rate-and-bases/]
    
+ Parameters

    + budgetPersonnelRateAndBaseId (optional) - Budget Personnel Rate And Base Id.
    + budgetPersonnelCalculatedAmountId (optional) - Budget Personnel Calculated Amount Id.
    + budgetPersonnelLineItemId (optional) - Budget Personnel Line Item Id.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + personNumber (optional) - Person Number.
    + salaryRequested (optional) - Salary Requested. Maximum length is 15.
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
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Personnel Rate And Bases [GET /research-common/api/v1/budget-personnel-rate-and-bases/]
	                                          
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
    
            {"columns":["budgetPersonnelRateAndBaseId","budgetPersonnelCalculatedAmountId","budgetPersonnelLineItemId","personId","personNumber","salaryRequested","baseCostSharing","budgetPeriodId","endDate","rateTypeCode","budgetId","rateNumber","appliedRate","budgetPeriod","calculatedCost","onOffCampusFlag","rateClassCode","calculatedCostSharing","lineItemNumber","startDate"],"primaryKey":"budgetPersonnelRateAndBaseId"}
		
### Get Blueprint API specification for Budget Personnel Rate And Bases [GET /research-common/api/v1/budget-personnel-rate-and-bases/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Personnel Rate And Bases.md"
            transfer-encoding:chunked
### Update Budget Personnel Rate And Bases [PUT /research-common/api/v1/budget-personnel-rate-and-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Personnel Rate And Bases [PUT /research-common/api/v1/budget-personnel-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Budget Personnel Rate And Bases [POST /research-common/api/v1/budget-personnel-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Personnel Rate And Bases [POST /research-common/api/v1/budget-personnel-rate-and-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelRateAndBaseId": "(val)","budgetPersonnelCalculatedAmountId": "(val)","budgetPersonnelLineItemId": "(val)","personId": "(val)","personNumber": "(val)","salaryRequested": "(val)","baseCostSharing": "(val)","budgetPeriodId": "(val)","endDate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateNumber": "(val)","appliedRate": "(val)","budgetPeriod": "(val)","calculatedCost": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","calculatedCostSharing": "(val)","lineItemNumber": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
### Delete Budget Personnel Rate And Bases by Key [DELETE /research-common/api/v1/budget-personnel-rate-and-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Rate And Bases [DELETE /research-common/api/v1/budget-personnel-rate-and-bases/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Rate And Bases with Matching [DELETE /research-common/api/v1/budget-personnel-rate-and-bases/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetPersonnelRateAndBaseId (optional) - Budget Personnel Rate And Base Id.
    + budgetPersonnelCalculatedAmountId (optional) - Budget Personnel Calculated Amount Id.
    + budgetPersonnelLineItemId (optional) - Budget Personnel Line Item Id.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + personNumber (optional) - Person Number.
    + salaryRequested (optional) - Salary Requested. Maximum length is 15.
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
