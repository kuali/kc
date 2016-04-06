## Award Budget Statuses [/research-sys/api/v1/award-budget-statuses/]

### Get Award Budget Statuses by Key [GET /research-sys/api/v1/award-budget-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Statuses [GET /research-sys/api/v1/award-budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Statuses with Filtering [GET /research-sys/api/v1/award-budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardBudgetStatusCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Statuses [GET /research-sys/api/v1/award-budget-statuses/]
	 
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
		
### Get Blueprint API specification for Award Budget Statuses [GET /research-sys/api/v1/award-budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Statuses.md"
            transfer-encoding:chunked


### Update Award Budget Statuses [PUT /research-sys/api/v1/award-budget-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Statuses [PUT /research-sys/api/v1/award-budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Statuses [POST /research-sys/api/v1/award-budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Statuses [POST /research-sys/api/v1/award-budget-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Statuses by Key [DELETE /research-sys/api/v1/award-budget-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Statuses [DELETE /research-sys/api/v1/award-budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Budget Statuses with Matching [DELETE /research-sys/api/v1/award-budget-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardBudgetStatusCode
            + description


+ Response 204
