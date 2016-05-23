## Protocol Reference Types [/irb/api/v1/protocol-reference-types/]

### Get Protocol Reference Types by Key [GET /irb/api/v1/protocol-reference-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Protocol Reference Types [GET /irb/api/v1/protocol-reference-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Reference Types with Filtering [GET /irb/api/v1/protocol-reference-types/]
    
+ Parameters

    + protocolReferenceTypeCode (optional) - Protocol Reference Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 300.
    + active (optional) - Is Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Reference Types [GET /irb/api/v1/protocol-reference-types/]
	                                          
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
    
            {"columns":["protocolReferenceTypeCode","description","active"],"primaryKey":"protocolReferenceTypeCode"}
		
### Get Blueprint API specification for Protocol Reference Types [GET /irb/api/v1/protocol-reference-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Reference Types.md"
            transfer-encoding:chunked
### Update Protocol Reference Types [PUT /irb/api/v1/protocol-reference-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Reference Types [PUT /irb/api/v1/protocol-reference-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Reference Types [POST /irb/api/v1/protocol-reference-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Reference Types [POST /irb/api/v1/protocol-reference-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Reference Types by Key [DELETE /irb/api/v1/protocol-reference-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Reference Types [DELETE /irb/api/v1/protocol-reference-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Reference Types with Matching [DELETE /irb/api/v1/protocol-reference-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolReferenceTypeCode (optional) - Protocol Reference Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 300.
    + active (optional) - Is Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
