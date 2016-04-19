## Budget Period Types [/research-common/api/v1/budget-period-types/]

### Get Budget Period Types by Key [GET /research-common/api/v1/budget-period-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Budget Period Types [GET /research-common/api/v1/budget-period-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Period Types with Filtering [GET /research-common/api/v1/budget-period-types/]
    
+ Parameters

    + budgetPeriodTypeCode (optional) - Budget Period Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Period Types [GET /research-common/api/v1/budget-period-types/]
	                                          
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
    
            {"columns":["budgetPeriodTypeCode","description"],"primaryKey":"budgetPeriodTypeCode"}
		
### Get Blueprint API specification for Budget Period Types [GET /research-common/api/v1/budget-period-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Period Types.md"
            transfer-encoding:chunked


### Update Budget Period Types [PUT /research-common/api/v1/budget-period-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Period Types [PUT /research-common/api/v1/budget-period-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Period Types [POST /research-common/api/v1/budget-period-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Period Types [POST /research-common/api/v1/budget-period-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Period Types by Key [DELETE /research-common/api/v1/budget-period-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Period Types [DELETE /research-common/api/v1/budget-period-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Period Types with Matching [DELETE /research-common/api/v1/budget-period-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetPeriodTypeCode (optional) - Budget Period Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
