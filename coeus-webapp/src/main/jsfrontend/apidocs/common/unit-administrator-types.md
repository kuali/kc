## Unit Administrator Types [/research-sys/api/v1/unit-administrator-types/]

### Get Unit Administrator Types by Key [GET /research-sys/api/v1/unit-administrator-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}

### Get All Unit Administrator Types [GET /research-sys/api/v1/unit-administrator-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Unit Administrator Types with Filtering [GET /research-sys/api/v1/unit-administrator-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + code
            + description
            + multiplesFlag
            + defaultGroupFlag
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Administrator Types [GET /research-sys/api/v1/unit-administrator-types/]
	 
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
		
### Get Blueprint API specification for Unit Administrator Types [GET /research-sys/api/v1/unit-administrator-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Administrator Types.md"
            transfer-encoding:chunked


### Update Unit Administrator Types [PUT /research-sys/api/v1/unit-administrator-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Administrator Types [PUT /research-sys/api/v1/unit-administrator-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Unit Administrator Types [POST /research-sys/api/v1/unit-administrator-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Administrator Types [POST /research-sys/api/v1/unit-administrator-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","multiplesFlag": "(val)","defaultGroupFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Unit Administrator Types by Key [DELETE /research-sys/api/v1/unit-administrator-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrator Types [DELETE /research-sys/api/v1/unit-administrator-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Unit Administrator Types with Matching [DELETE /research-sys/api/v1/unit-administrator-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + code
            + description
            + multiplesFlag
            + defaultGroupFlag


+ Response 204
