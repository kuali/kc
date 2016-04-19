## Risk Levels [/research-common/api/v1/risk-levels/]

### Get Risk Levels by Key [GET /research-common/api/v1/risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Risk Levels [GET /research-common/api/v1/risk-levels/]
	 
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

### Get All Risk Levels with Filtering [GET /research-common/api/v1/risk-levels/]
    
+ Parameters

    + riskLevelCode (optional) - Risk Level Code. Maximum length is 3.
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
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Risk Levels [GET /research-common/api/v1/risk-levels/]
	                                          
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
    
            {"columns":["riskLevelCode","description"],"primaryKey":"riskLevelCode"}
		
### Get Blueprint API specification for Risk Levels [GET /research-common/api/v1/risk-levels/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Risk Levels.md"
            transfer-encoding:chunked


### Update Risk Levels [PUT /research-common/api/v1/risk-levels/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Risk Levels [PUT /research-common/api/v1/risk-levels/]

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

### Insert Risk Levels [POST /research-common/api/v1/risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"riskLevelCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Risk Levels [POST /research-common/api/v1/risk-levels/]

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
            
### Delete Risk Levels by Key [DELETE /research-common/api/v1/risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Risk Levels [DELETE /research-common/api/v1/risk-levels/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Risk Levels with Matching [DELETE /research-common/api/v1/risk-levels/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + riskLevelCode (optional) - Risk Level Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
