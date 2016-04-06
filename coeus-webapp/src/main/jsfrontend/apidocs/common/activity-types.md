## Activity Types [/research-sys/api/v1/activity-types/]

### Get Activity Types by Key [GET /research-sys/api/v1/activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}

### Get All Activity Types [GET /research-sys/api/v1/activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Activity Types with Filtering [GET /research-sys/api/v1/activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + code
            + description
            + higherEducationFunctionCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Activity Types [GET /research-sys/api/v1/activity-types/]
	 
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
		
### Get Blueprint API specification for Activity Types [GET /research-sys/api/v1/activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Activity Types.md"
            transfer-encoding:chunked


### Update Activity Types [PUT /research-sys/api/v1/activity-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Activity Types [PUT /research-sys/api/v1/activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Activity Types [POST /research-sys/api/v1/activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Activity Types [POST /research-sys/api/v1/activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Activity Types by Key [DELETE /research-sys/api/v1/activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Activity Types [DELETE /research-sys/api/v1/activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Activity Types with Matching [DELETE /research-sys/api/v1/activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + code
            + description
            + higherEducationFunctionCode


+ Response 204
