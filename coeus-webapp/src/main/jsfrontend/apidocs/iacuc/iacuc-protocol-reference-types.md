## Iacuc Protocol Reference Types [/iacuc/api/v1/iacuc-protocol-reference-types/]

### Get Iacuc Protocol Reference Types by Key [GET /iacuc/api/v1/iacuc-protocol-reference-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Reference Types [GET /iacuc/api/v1/iacuc-protocol-reference-types/]
	 
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

### Get All Iacuc Protocol Reference Types with Filtering [GET /iacuc/api/v1/iacuc-protocol-reference-types/]
    
+ Parameters

    + protocolReferenceTypeCode (optional) - Protocol Reference Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - 

            
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
			
### Get Schema for Iacuc Protocol Reference Types [GET /iacuc/api/v1/iacuc-protocol-reference-types/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Reference Types [GET /iacuc/api/v1/iacuc-protocol-reference-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Reference Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Reference Types [PUT /iacuc/api/v1/iacuc-protocol-reference-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Reference Types [PUT /iacuc/api/v1/iacuc-protocol-reference-types/]

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

### Insert Iacuc Protocol Reference Types [POST /iacuc/api/v1/iacuc-protocol-reference-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReferenceTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Reference Types [POST /iacuc/api/v1/iacuc-protocol-reference-types/]

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
            
### Delete Iacuc Protocol Reference Types by Key [DELETE /iacuc/api/v1/iacuc-protocol-reference-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Reference Types [DELETE /iacuc/api/v1/iacuc-protocol-reference-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Reference Types with Matching [DELETE /iacuc/api/v1/iacuc-protocol-reference-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolReferenceTypeCode (optional) - Protocol Reference Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
