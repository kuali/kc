## Award Budget Types [/research-sys/api/v1/award-budget-types/]

### Get Award Budget Types by Key [GET /research-sys/api/v1/award-budget-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Types [GET /research-sys/api/v1/award-budget-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Types with Filtering [GET /research-sys/api/v1/award-budget-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardBudgetTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Types [GET /research-sys/api/v1/award-budget-types/]
	 
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
		
### Get Blueprint API specification for Award Budget Types [GET /research-sys/api/v1/award-budget-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Types.md"
            transfer-encoding:chunked


### Update Award Budget Types [PUT /research-sys/api/v1/award-budget-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Types [PUT /research-sys/api/v1/award-budget-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Types [POST /research-sys/api/v1/award-budget-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Types [POST /research-sys/api/v1/award-budget-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"awardBudgetTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Types by Key [DELETE /research-sys/api/v1/award-budget-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Types [DELETE /research-sys/api/v1/award-budget-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Budget Types with Matching [DELETE /research-sys/api/v1/award-budget-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardBudgetTypeCode
            + description


+ Response 204
