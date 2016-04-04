## Award Budget Limits [/research-sys/api/v1/award-budget-limits/]

### Get Award Budget Limits by Key [GET /research-sys/api/v1/award-budget-limits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Limits [GET /research-sys/api/v1/award-budget-limits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"},
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Limits with Filtering [GET /research-sys/api/v1/award-budget-limits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetLimitId
            + awardId
            + budgetId
            + limitTypeCode
            + limit
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"},
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Limits [GET /research-sys/api/v1/award-budget-limits/]
	 
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
		
### Get Blueprint API specification for Award Budget Limits [GET /research-sys/api/v1/award-budget-limits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Limits.md"
            transfer-encoding:chunked


### Update Award Budget Limits [PUT /research-sys/api/v1/award-budget-limits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Limits [PUT /research-sys/api/v1/award-budget-limits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"},
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Limits [POST /research-sys/api/v1/award-budget-limits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Limits [POST /research-sys/api/v1/award-budget-limits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"},
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"},
              {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Limits by Key [DELETE /research-sys/api/v1/award-budget-limits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Limits [DELETE /research-sys/api/v1/award-budget-limits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Budget Limits with Matching [DELETE /research-sys/api/v1/award-budget-limits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetLimitId
            + awardId
            + budgetId
            + limitTypeCode
            + limit


+ Response 204
