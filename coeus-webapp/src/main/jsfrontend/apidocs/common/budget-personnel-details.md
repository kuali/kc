## Budget Personnel Details [/research-sys/api/v1/budget-personnel-details/]

### Get Budget Personnel Details by Key [GET /research-sys/api/v1/budget-personnel-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}

### Get All Budget Personnel Details [GET /research-sys/api/v1/budget-personnel-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Personnel Details with Filtering [GET /research-sys/api/v1/budget-personnel-details/]
    
+ Parameters

        + budgetPersonnelLineItemId
            + budgetLineItemId
            + lineItemNumber
            + budgetId
            + budgetPeriod
            + onOffCampusFlag
            + endDate
            + startDate
            + budgetJustification
            + costSharingAmount
            + lineItemDescription
            + applyInRateFlag
            + personNumber
            + costSharingPercent
            + jobCode
            + percentCharged
            + percentEffort
            + periodTypeCode
            + personId
            + salaryRequested
            + sequenceNumber
            + budgetPeriodId
            + personSequenceNumber
            + underrecoveryAmount
            + submitCostSharingFlag

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Personnel Details [GET /research-sys/api/v1/budget-personnel-details/]
	                                          
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
    
            {"columns":["budgetPersonnelLineItemId","budgetLineItemId","lineItemNumber","budgetId","budgetPeriod","onOffCampusFlag","endDate","startDate","budgetJustification","costSharingAmount","lineItemDescription","applyInRateFlag","personNumber","costSharingPercent","jobCode","percentCharged","percentEffort","periodTypeCode","personId","salaryRequested","sequenceNumber","budgetPeriodId","personSequenceNumber","underrecoveryAmount","submitCostSharingFlag"],"primaryKey":"budgetPersonnelLineItemId"}
		
### Get Blueprint API specification for Budget Personnel Details [GET /research-sys/api/v1/budget-personnel-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Personnel Details.md"
            transfer-encoding:chunked


### Update Budget Personnel Details [PUT /research-sys/api/v1/budget-personnel-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Personnel Details [PUT /research-sys/api/v1/budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Personnel Details [POST /research-sys/api/v1/budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Personnel Details [POST /research-sys/api/v1/budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","budgetLineItemId": "(val)","lineItemNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","onOffCampusFlag": "(val)","endDate": "(val)","startDate": "(val)","budgetJustification": "(val)","costSharingAmount": "(val)","lineItemDescription": "(val)","applyInRateFlag": "(val)","personNumber": "(val)","costSharingPercent": "(val)","jobCode": "(val)","percentCharged": "(val)","percentEffort": "(val)","periodTypeCode": "(val)","personId": "(val)","salaryRequested": "(val)","sequenceNumber": "(val)","budgetPeriodId": "(val)","personSequenceNumber": "(val)","underrecoveryAmount": "(val)","submitCostSharingFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Personnel Details by Key [DELETE /research-sys/api/v1/budget-personnel-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Details [DELETE /research-sys/api/v1/budget-personnel-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Personnel Details with Matching [DELETE /research-sys/api/v1/budget-personnel-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetPersonnelLineItemId
            + budgetLineItemId
            + lineItemNumber
            + budgetId
            + budgetPeriod
            + onOffCampusFlag
            + endDate
            + startDate
            + budgetJustification
            + costSharingAmount
            + lineItemDescription
            + applyInRateFlag
            + personNumber
            + costSharingPercent
            + jobCode
            + percentCharged
            + percentEffort
            + periodTypeCode
            + personId
            + salaryRequested
            + sequenceNumber
            + budgetPeriodId
            + personSequenceNumber
            + underrecoveryAmount
            + submitCostSharingFlag

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
