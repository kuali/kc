## Iacuc Committee Schedule Attachments [/research-sys/api/v1/iacuc-committee-schedule-attachments/]

### Get Iacuc Committee Schedule Attachments by Key [GET /research-sys/api/v1/iacuc-committee-schedule-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Attachments [GET /research-sys/api/v1/iacuc-committee-schedule-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"},
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committee Schedule Attachments with Filtering [GET /research-sys/api/v1/iacuc-committee-schedule-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + scheduleId
            + attachmentId
            + attachmentsTypeCode
            + description
            + fileName
            + document
            + mimeType
            + newUpdateTimestamp
            + newUpdateUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"},
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Schedule Attachments [GET /research-sys/api/v1/iacuc-committee-schedule-attachments/]
	 
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
		
### Get Blueprint API specification for Iacuc Committee Schedule Attachments [GET /research-sys/api/v1/iacuc-committee-schedule-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Attachments.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedule Attachments [PUT /research-sys/api/v1/iacuc-committee-schedule-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Attachments [PUT /research-sys/api/v1/iacuc-committee-schedule-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"},
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Committee Schedule Attachments [POST /research-sys/api/v1/iacuc-committee-schedule-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Attachments [POST /research-sys/api/v1/iacuc-committee-schedule-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"},
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"},
              {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Committee Schedule Attachments by Key [DELETE /research-sys/api/v1/iacuc-committee-schedule-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Attachments [DELETE /research-sys/api/v1/iacuc-committee-schedule-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Committee Schedule Attachments with Matching [DELETE /research-sys/api/v1/iacuc-committee-schedule-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + scheduleId
            + attachmentId
            + attachmentsTypeCode
            + description
            + fileName
            + document
            + mimeType
            + newUpdateTimestamp
            + newUpdateUser


+ Response 204
