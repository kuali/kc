## Sub Award Attachments [/research-sys/api/v1/sub-award-attachments/]

### Get Sub Award Attachments by Key [GET /research-sys/api/v1/sub-award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Attachments [GET /research-sys/api/v1/sub-award-attachments/]
	 
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

### Get All Sub Award Attachments with Filtering [GET /research-sys/api/v1/sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + attachmentId
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + subAwardAttachmentTypeCode
            + documentId
            + fileDataId
            + fileName
            + mimeType
            + documentStatusCode
            + description
            + lastUpdateTimestamp
            + lastUpdateUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Attachments [GET /research-sys/api/v1/sub-award-attachments/]
	 
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
		
### Get Blueprint API specification for Sub Award Attachments [GET /research-sys/api/v1/sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Attachments.md"
            transfer-encoding:chunked


### Update Sub Award Attachments [PUT /research-sys/api/v1/sub-award-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Attachments [PUT /research-sys/api/v1/sub-award-attachments/]

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

### Insert Sub Award Attachments [POST /research-sys/api/v1/sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardAttachmentTypeCode": "(val)","documentId": "(val)","fileDataId": "(val)","fileName": "(val)","mimeType": "(val)","documentStatusCode": "(val)","description": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Attachments [POST /research-sys/api/v1/sub-award-attachments/]

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
            
### Delete Sub Award Attachments by Key [DELETE /research-sys/api/v1/sub-award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Attachments [DELETE /research-sys/api/v1/sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Attachments with Matching [DELETE /research-sys/api/v1/sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + attachmentId
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + subAwardAttachmentTypeCode
            + documentId
            + fileDataId
            + fileName
            + mimeType
            + documentStatusCode
            + description
            + lastUpdateTimestamp
            + lastUpdateUser


+ Response 204
