## Iacuc Committee Schedule Attachments [/iacuc/api/v1/iacuc-committee-schedule-attachments/]

### Get Iacuc Committee Schedule Attachments by Key [GET /iacuc/api/v1/iacuc-committee-schedule-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Attachments [GET /iacuc/api/v1/iacuc-committee-schedule-attachments/]
	 
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

### Get All Iacuc Committee Schedule Attachments with Filtering [GET /iacuc/api/v1/iacuc-committee-schedule-attachments/]
    
+ Parameters

    + scheduleId (optional) - 
    + attachmentId (optional) - 
    + attachmentsTypeCode (optional) - 
    + description (optional) - 
    + fileName (optional) - 
    + document (optional) - 
    + mimeType (optional) - 
    + newUpdateTimestamp (optional) - 
    + newUpdateUser (optional) - 

            
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
			
### Get Schema for Iacuc Committee Schedule Attachments [GET /iacuc/api/v1/iacuc-committee-schedule-attachments/]
	                                          
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
    
            {"columns":["scheduleId","attachmentId","attachmentsTypeCode","description","fileName","document","mimeType","newUpdateTimestamp","newUpdateUser"],"primaryKey":"attachmentId"}
		
### Get Blueprint API specification for Iacuc Committee Schedule Attachments [GET /iacuc/api/v1/iacuc-committee-schedule-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Attachments.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedule Attachments [PUT /iacuc/api/v1/iacuc-committee-schedule-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Attachments [PUT /iacuc/api/v1/iacuc-committee-schedule-attachments/]

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

### Insert Iacuc Committee Schedule Attachments [POST /iacuc/api/v1/iacuc-committee-schedule-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Attachments [POST /iacuc/api/v1/iacuc-committee-schedule-attachments/]

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
            
### Delete Iacuc Committee Schedule Attachments by Key [DELETE /iacuc/api/v1/iacuc-committee-schedule-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Attachments [DELETE /iacuc/api/v1/iacuc-committee-schedule-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Attachments with Matching [DELETE /iacuc/api/v1/iacuc-committee-schedule-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + scheduleId (optional) - 
    + attachmentId (optional) - 
    + attachmentsTypeCode (optional) - 
    + description (optional) - 
    + fileName (optional) - 
    + document (optional) - 
    + mimeType (optional) - 
    + newUpdateTimestamp (optional) - 
    + newUpdateUser (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
