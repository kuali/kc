## Protocol Modules [/irb/api/v1/protocol-modules/]

### Get Protocol Modules by Key [GET /irb/api/v1/protocol-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Modules [GET /irb/api/v1/protocol-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Modules with Filtering [GET /irb/api/v1/protocol-modules/]
    
+ Parameters

    + protocolModuleCode (optional) - 
    + description (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Modules [GET /irb/api/v1/protocol-modules/]
	                                          
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
    
            {"columns":["protocolModuleCode","description"],"primaryKey":"protocolModuleCode"}
		
### Get Blueprint API specification for Protocol Modules [GET /irb/api/v1/protocol-modules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Modules.md"
            transfer-encoding:chunked


### Update Protocol Modules [PUT /irb/api/v1/protocol-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Modules [PUT /irb/api/v1/protocol-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Modules [POST /irb/api/v1/protocol-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Modules [POST /irb/api/v1/protocol-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Modules by Key [DELETE /irb/api/v1/protocol-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Modules [DELETE /irb/api/v1/protocol-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Modules with Matching [DELETE /irb/api/v1/protocol-modules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolModuleCode (optional) - 
    + description (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
