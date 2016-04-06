## Budget Period Types [/research-sys/api/v1/budget-period-types/]

### Get Budget Period Types by Key [GET /research-sys/api/v1/budget-period-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Budget Period Types [GET /research-sys/api/v1/budget-period-types/]
	 
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

### Get All Budget Period Types with Filtering [GET /research-sys/api/v1/budget-period-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetPeriodTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Period Types [GET /research-sys/api/v1/budget-period-types/]
	 
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
		
### Get Blueprint API specification for Budget Period Types [GET /research-sys/api/v1/budget-period-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Period Types.md"
            transfer-encoding:chunked


### Update Budget Period Types [PUT /research-sys/api/v1/budget-period-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Period Types [PUT /research-sys/api/v1/budget-period-types/]

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

### Insert Budget Period Types [POST /research-sys/api/v1/budget-period-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPeriodTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Period Types [POST /research-sys/api/v1/budget-period-types/]

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
            
### Delete Budget Period Types by Key [DELETE /research-sys/api/v1/budget-period-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Period Types [DELETE /research-sys/api/v1/budget-period-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Period Types with Matching [DELETE /research-sys/api/v1/budget-period-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetPeriodTypeCode
            + description


+ Response 204
