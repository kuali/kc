## Iacuc Protocol Statuses [/research-sys/api/v1/iacuc-protocol-statuses/]

### Get Iacuc Protocol Statuses by Key [GET /research-sys/api/v1/iacuc-protocol-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Statuses [GET /research-sys/api/v1/iacuc-protocol-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Statuses with Filtering [GET /research-sys/api/v1/iacuc-protocol-statuses/]
    
+ Parameters

        + protocolStatusCode
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Statuses [GET /research-sys/api/v1/iacuc-protocol-statuses/]
	                                          
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
    
            {"columns":["protocolStatusCode","description"],"primaryKey":"protocolStatusCode"}
		
### Get Blueprint API specification for Iacuc Protocol Statuses [GET /research-sys/api/v1/iacuc-protocol-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Statuses.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Statuses [PUT /research-sys/api/v1/iacuc-protocol-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Statuses [PUT /research-sys/api/v1/iacuc-protocol-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Statuses [POST /research-sys/api/v1/iacuc-protocol-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Statuses [POST /research-sys/api/v1/iacuc-protocol-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Statuses by Key [DELETE /research-sys/api/v1/iacuc-protocol-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Statuses [DELETE /research-sys/api/v1/iacuc-protocol-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Statuses with Matching [DELETE /research-sys/api/v1/iacuc-protocol-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolStatusCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
