## Sub Award Forms [/subaward/api/v1/sub-award-forms/]

### Get Sub Award Forms by Key [GET /subaward/api/v1/sub-award-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Forms [GET /subaward/api/v1/sub-award-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"},
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Forms with Filtering [GET /subaward/api/v1/sub-award-forms/]
    
+ Parameters

    + formId (optional) - Form ID. Maximum length is 30.
    + description (optional) - Description. Maximum length is 200.
    + attachmentContent (optional) - 
    + fileName (optional) - File Name. Maximum length is 150.
    + contentType (optional) - Content Type. Maximum length is 150.
    + templateTypeCode (optional) - templateTypeCode. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"},
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Forms [GET /subaward/api/v1/sub-award-forms/]
	                                          
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
    
            {"columns":["formId","description","attachmentContent","fileName","contentType","templateTypeCode"],"primaryKey":"formId"}
		
### Get Blueprint API specification for Sub Award Forms [GET /subaward/api/v1/sub-award-forms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Forms.md"
            transfer-encoding:chunked
### Update Sub Award Forms [PUT /subaward/api/v1/sub-award-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Forms [PUT /subaward/api/v1/sub-award-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"},
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Forms [POST /subaward/api/v1/sub-award-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Forms [POST /subaward/api/v1/sub-award-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"},
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"},
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Forms by Key [DELETE /subaward/api/v1/sub-award-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Forms [DELETE /subaward/api/v1/sub-award-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Forms with Matching [DELETE /subaward/api/v1/sub-award-forms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + formId (optional) - Form ID. Maximum length is 30.
    + description (optional) - Description. Maximum length is 200.
    + attachmentContent (optional) - 
    + fileName (optional) - File Name. Maximum length is 150.
    + contentType (optional) - Content Type. Maximum length is 150.
    + templateTypeCode (optional) - templateTypeCode. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
