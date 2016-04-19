## Award Budget Limits [/award/api/v1/award-budget-limits/]

### Get Award Budget Limits by Key [GET /award/api/v1/award-budget-limits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Limits [GET /award/api/v1/award-budget-limits/]
	 
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

### Get All Award Budget Limits with Filtering [GET /award/api/v1/award-budget-limits/]
    
+ Parameters

    + budgetLimitId (optional) - Award Budget Limit Id. Maximum length is 22.
    + awardId (optional) - 
    + budgetId (optional) - 
    + limitTypeCode (optional) - Budget Limit Type Code. Maximum length is 100.
    + limit (optional) - Budget Limit. Maximum length is 15.

            
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
			
### Get Schema for Award Budget Limits [GET /award/api/v1/award-budget-limits/]
	                                          
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
    
            {"columns":["budgetLimitId","awardId","budgetId","limitTypeCode","limit"],"primaryKey":"budgetLimitId"}
		
### Get Blueprint API specification for Award Budget Limits [GET /award/api/v1/award-budget-limits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Limits.md"
            transfer-encoding:chunked


### Update Award Budget Limits [PUT /award/api/v1/award-budget-limits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Limits [PUT /award/api/v1/award-budget-limits/]

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

### Insert Award Budget Limits [POST /award/api/v1/award-budget-limits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetLimitId": "(val)","awardId": "(val)","budgetId": "(val)","limitTypeCode": "(val)","limit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Limits [POST /award/api/v1/award-budget-limits/]

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
            
### Delete Award Budget Limits by Key [DELETE /award/api/v1/award-budget-limits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Limits [DELETE /award/api/v1/award-budget-limits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Limits with Matching [DELETE /award/api/v1/award-budget-limits/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetLimitId (optional) - Award Budget Limit Id. Maximum length is 22.
    + awardId (optional) - 
    + budgetId (optional) - 
    + limitTypeCode (optional) - Budget Limit Type Code. Maximum length is 100.
    + limit (optional) - Budget Limit. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
