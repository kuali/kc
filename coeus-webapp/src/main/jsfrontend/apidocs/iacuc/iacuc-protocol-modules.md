## Iacuc Protocol Modules [/iacuc/api/v1/iacuc-protocol-modules/]

### Get Iacuc Protocol Modules by Key [GET /iacuc/api/v1/iacuc-protocol-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Modules [GET /iacuc/api/v1/iacuc-protocol-modules/]
	 
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

### Get All Iacuc Protocol Modules with Filtering [GET /iacuc/api/v1/iacuc-protocol-modules/]
    
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
			
### Get Schema for Iacuc Protocol Modules [GET /iacuc/api/v1/iacuc-protocol-modules/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Modules [GET /iacuc/api/v1/iacuc-protocol-modules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Modules.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Modules [PUT /iacuc/api/v1/iacuc-protocol-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Modules [PUT /iacuc/api/v1/iacuc-protocol-modules/]

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
### Insert Iacuc Protocol Modules [POST /iacuc/api/v1/iacuc-protocol-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolModuleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Modules [POST /iacuc/api/v1/iacuc-protocol-modules/]

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
### Delete Iacuc Protocol Modules by Key [DELETE /iacuc/api/v1/iacuc-protocol-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Modules [DELETE /iacuc/api/v1/iacuc-protocol-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Modules with Matching [DELETE /iacuc/api/v1/iacuc-protocol-modules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolModuleCode (optional) - 
    + description (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
