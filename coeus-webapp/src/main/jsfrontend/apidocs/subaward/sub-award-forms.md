## Sub Award Forms [/research-sys/api/v1/sub-award-forms/]

### Get Sub Award Forms by Key [GET /research-sys/api/v1/sub-award-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Forms [GET /research-sys/api/v1/sub-award-forms/]
	 
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

### Get All Sub Award Forms with Filtering [GET /research-sys/api/v1/sub-award-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + formId
            + description
            + attachmentContent
            + fileName
            + contentType
            + templateTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"},
              {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Forms [GET /research-sys/api/v1/sub-award-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Sub Award Forms [GET /research-sys/api/v1/sub-award-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Forms.md"
            transfer-encoding:chunked


### Update Sub Award Forms [PUT /research-sys/api/v1/sub-award-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Forms [PUT /research-sys/api/v1/sub-award-forms/]

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

### Insert Sub Award Forms [POST /research-sys/api/v1/sub-award-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"formId": "(val)","description": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","templateTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Forms [POST /research-sys/api/v1/sub-award-forms/]

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
            
### Delete Sub Award Forms by Key [DELETE /research-sys/api/v1/sub-award-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Forms [DELETE /research-sys/api/v1/sub-award-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Forms with Matching [DELETE /research-sys/api/v1/sub-award-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + formId
            + description
            + attachmentContent
            + fileName
            + contentType
            + templateTypeCode


+ Response 204
