## Risk Levels [/research-sys/api/v1/risk-levels/]

### Get Risk Levels by Key [GET /research-sys/api/v1/risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Risk Levels [GET /research-sys/api/v1/risk-levels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Risk Levels with Filtering [GET /research-sys/api/v1/risk-levels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + riskLevelCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Risk Levels [GET /research-sys/api/v1/risk-levels/]
	 
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
		
### Get Blueprint API specification for Risk Levels [GET /research-sys/api/v1/risk-levels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Risk Levels.md"
            transfer-encoding:chunked


### Update Risk Levels [PUT /research-sys/api/v1/risk-levels/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Risk Levels [PUT /research-sys/api/v1/risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Risk Levels [POST /research-sys/api/v1/risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Risk Levels [POST /research-sys/api/v1/risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Risk Levels by Key [DELETE /research-sys/api/v1/risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Risk Levels [DELETE /research-sys/api/v1/risk-levels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Risk Levels with Matching [DELETE /research-sys/api/v1/risk-levels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + riskLevelCode
            + description


+ Response 204
