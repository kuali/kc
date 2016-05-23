## Sub Award Attachments [/subaward/api/v1/sub-award-attachments/]

### Get Sub Award Attachments by Key [GET /subaward/api/v1/sub-award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Attachments [GET /subaward/api/v1/sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Attachments with Filtering [GET /subaward/api/v1/sub-award-attachments/]
    
+ Parameters

    + attachmentId (optional) - Attachment ID. Maximum length is 15.
    + subAwardId (optional) - Subaward ID. Maximum length is 22.
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardAttachmentTypeCode (optional) - Attachment Type. Maximum length is 3.
    + documentId (optional) - Document Id. Maximum length is 4.
    + fileDataId (optional) - 
    + fileName (optional) - File Name. Maximum length is 150.
    + mimeType (optional) - Mime Type. Maximum length is 4000.
    + documentStatusCode (optional) - 
    + description (optional) - Description. Maximum length is 200.
    + lastUpdateTimestamp (optional) - The creation or last modification timestamp. Maximum length is 21.
    + lastUpdateUser (optional) - The user who created or last modified the object. Maximum length is 60.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Attachments [GET /subaward/api/v1/sub-award-attachments/]
	                                          
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
    
            {"columns":["attachmentId","subAwardId","subAwardCode","sequenceNumber","subAwardAttachmentTypeCode","documentId","fileDataId","fileName","mimeType","documentStatusCode","description","lastUpdateTimestamp","lastUpdateUser"],"primaryKey":"attachmentId"}
		
### Get Blueprint API specification for Sub Award Attachments [GET /subaward/api/v1/sub-award-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Attachments.md"
            transfer-encoding:chunked
### Update Sub Award Attachments [PUT /subaward/api/v1/sub-award-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Attachments [PUT /subaward/api/v1/sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Attachments [POST /subaward/api/v1/sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Attachments [POST /subaward/api/v1/sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Attachments by Key [DELETE /subaward/api/v1/sub-award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Attachments [DELETE /subaward/api/v1/sub-award-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Attachments with Matching [DELETE /subaward/api/v1/sub-award-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + attachmentId (optional) - Attachment ID. Maximum length is 15.
    + subAwardId (optional) - Subaward ID. Maximum length is 22.
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardAttachmentTypeCode (optional) - Attachment Type. Maximum length is 3.
    + documentId (optional) - Document Id. Maximum length is 4.
    + fileDataId (optional) - 
    + fileName (optional) - File Name. Maximum length is 150.
    + mimeType (optional) - Mime Type. Maximum length is 4000.
    + documentStatusCode (optional) - 
    + description (optional) - Description. Maximum length is 200.
    + lastUpdateTimestamp (optional) - The creation or last modification timestamp. Maximum length is 21.
    + lastUpdateUser (optional) - The user who created or last modified the object. Maximum length is 60.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
