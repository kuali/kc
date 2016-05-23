## Iacuc Protocol Attachment Type Groups [/iacuc/api/v1/iacuc-protocol-attachment-type-groups/]

### Get Iacuc Protocol Attachment Type Groups by Key [GET /iacuc/api/v1/iacuc-protocol-attachment-type-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Attachment Type Groups [GET /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Attachment Type Groups with Filtering [GET /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]
    
+ Parameters

    + id (optional) - id. Maximum length is 12.
    + typeCode (optional) - Type Code. Maximum length is 3.
    + groupCode (optional) - Group Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Attachment Type Groups [GET /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]
	                                          
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
    
            {"columns":["id","typeCode","groupCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Protocol Attachment Type Groups [GET /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Attachment Type Groups.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Attachment Type Groups [PUT /iacuc/api/v1/iacuc-protocol-attachment-type-groups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Attachment Type Groups [PUT /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Attachment Type Groups [POST /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Attachment Type Groups [POST /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","typeCode": "(val)","groupCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Attachment Type Groups by Key [DELETE /iacuc/api/v1/iacuc-protocol-attachment-type-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Attachment Type Groups [DELETE /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Attachment Type Groups with Matching [DELETE /iacuc/api/v1/iacuc-protocol-attachment-type-groups/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - id. Maximum length is 12.
    + typeCode (optional) - Type Code. Maximum length is 3.
    + groupCode (optional) - Group Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
