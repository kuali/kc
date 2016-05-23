## Sub Award Attachment Types [/subaward/api/v1/sub-award-attachment-types/]

### Get Sub Award Attachment Types by Key [GET /subaward/api/v1/sub-award-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Attachment Types [GET /subaward/api/v1/sub-award-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Attachment Types with Filtering [GET /subaward/api/v1/sub-award-attachment-types/]
    
+ Parameters

    + subAwardAttachmentTypeCode (optional) - Subaward Attachment Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Attachment Types [GET /subaward/api/v1/sub-award-attachment-types/]
	                                          
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
    
            {"columns":["subAwardAttachmentTypeCode","description"],"primaryKey":"subAwardAttachmentTypeCode"}
		
### Get Blueprint API specification for Sub Award Attachment Types [GET /subaward/api/v1/sub-award-attachment-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Attachment Types.md"
            transfer-encoding:chunked
### Update Sub Award Attachment Types [PUT /subaward/api/v1/sub-award-attachment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Attachment Types [PUT /subaward/api/v1/sub-award-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Attachment Types [POST /subaward/api/v1/sub-award-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Attachment Types [POST /subaward/api/v1/sub-award-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardAttachmentTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Attachment Types by Key [DELETE /subaward/api/v1/sub-award-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Attachment Types [DELETE /subaward/api/v1/sub-award-attachment-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Attachment Types with Matching [DELETE /subaward/api/v1/sub-award-attachment-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardAttachmentTypeCode (optional) - Subaward Attachment Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
