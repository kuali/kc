## Protocol Types [/research-sys/api/v1/protocol-types/]

### Get Protocol Types by Key [GET /research-sys/api/v1/protocol-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}

### Get All Protocol Types [GET /research-sys/api/v1/protocol-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Types with Filtering [GET /research-sys/api/v1/protocol-types/]
    
+ Parameters

        + protocolTypeCode
            + description
            + globalFlag

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Types [GET /research-sys/api/v1/protocol-types/]
	                                          
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
    
            {"columns":["protocolTypeCode","description","globalFlag"],"primaryKey":"protocolTypeCode"}
		
### Get Blueprint API specification for Protocol Types [GET /research-sys/api/v1/protocol-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Types.md"
            transfer-encoding:chunked


### Update Protocol Types [PUT /research-sys/api/v1/protocol-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Types [PUT /research-sys/api/v1/protocol-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Types [POST /research-sys/api/v1/protocol-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Types [POST /research-sys/api/v1/protocol-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"protocolTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Types by Key [DELETE /research-sys/api/v1/protocol-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Types [DELETE /research-sys/api/v1/protocol-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Types with Matching [DELETE /research-sys/api/v1/protocol-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolTypeCode
            + description
            + globalFlag

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
