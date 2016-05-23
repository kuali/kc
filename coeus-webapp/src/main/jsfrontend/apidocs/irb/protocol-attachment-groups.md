## Protocol Attachment Groups [/irb/api/v1/protocol-attachment-groups/]

### Get Protocol Attachment Groups by Key [GET /irb/api/v1/protocol-attachment-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Attachment Groups [GET /irb/api/v1/protocol-attachment-groups/]
	 
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

### Get All Protocol Attachment Groups with Filtering [GET /irb/api/v1/protocol-attachment-groups/]
    
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
			
### Get Schema for Protocol Attachment Groups [GET /irb/api/v1/protocol-attachment-groups/]
	                                          
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
		
### Get Blueprint API specification for Protocol Attachment Groups [GET /irb/api/v1/protocol-attachment-groups/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Attachment Groups.md"
            transfer-encoding:chunked
### Update Protocol Attachment Groups [PUT /irb/api/v1/protocol-attachment-groups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Attachment Groups [PUT /irb/api/v1/protocol-attachment-groups/]

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
### Insert Protocol Attachment Groups [POST /irb/api/v1/protocol-attachment-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Attachment Groups [POST /irb/api/v1/protocol-attachment-groups/]

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
### Delete Protocol Attachment Groups by Key [DELETE /irb/api/v1/protocol-attachment-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Attachment Groups [DELETE /irb/api/v1/protocol-attachment-groups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Attachment Groups with Matching [DELETE /irb/api/v1/protocol-attachment-groups/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
