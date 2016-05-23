## Iacuc Protocol Attachment Statuses [/iacuc/api/v1/iacuc-protocol-attachment-statuses/]

### Get Iacuc Protocol Attachment Statuses by Key [GET /iacuc/api/v1/iacuc-protocol-attachment-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Attachment Statuses [GET /iacuc/api/v1/iacuc-protocol-attachment-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Attachment Statuses with Filtering [GET /iacuc/api/v1/iacuc-protocol-attachment-statuses/]
    
+ Parameters

    + code (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Attachment Statuses [GET /iacuc/api/v1/iacuc-protocol-attachment-statuses/]
	                                          
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
    
            {"columns":["code","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Iacuc Protocol Attachment Statuses [GET /iacuc/api/v1/iacuc-protocol-attachment-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Attachment Statuses.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Attachment Statuses [PUT /iacuc/api/v1/iacuc-protocol-attachment-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Attachment Statuses [PUT /iacuc/api/v1/iacuc-protocol-attachment-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Attachment Statuses [POST /iacuc/api/v1/iacuc-protocol-attachment-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Attachment Statuses [POST /iacuc/api/v1/iacuc-protocol-attachment-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Attachment Statuses by Key [DELETE /iacuc/api/v1/iacuc-protocol-attachment-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Attachment Statuses [DELETE /iacuc/api/v1/iacuc-protocol-attachment-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Attachment Statuses with Matching [DELETE /iacuc/api/v1/iacuc-protocol-attachment-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
