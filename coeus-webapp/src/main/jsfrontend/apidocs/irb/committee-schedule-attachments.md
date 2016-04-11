## Committee Schedule Attachments [/research-sys/api/v1/committee-schedule-attachments/]

### Get Committee Schedule Attachments by Key [GET /research-sys/api/v1/committee-schedule-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedule Attachments [GET /research-sys/api/v1/committee-schedule-attachments/]
	 
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

### Get All Committee Schedule Attachments with Filtering [GET /research-sys/api/v1/committee-schedule-attachments/]
    
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
			
### Get Schema for Committee Schedule Attachments [GET /research-sys/api/v1/committee-schedule-attachments/]
	                                          
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
		
### Get Blueprint API specification for Committee Schedule Attachments [GET /research-sys/api/v1/committee-schedule-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Schedule Attachments.md"
            transfer-encoding:chunked


### Update Committee Schedule Attachments [PUT /research-sys/api/v1/committee-schedule-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedule Attachments [PUT /research-sys/api/v1/committee-schedule-attachments/]

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

### Insert Committee Schedule Attachments [POST /research-sys/api/v1/committee-schedule-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleId": "(val)","attachmentId": "(val)","attachmentsTypeCode": "(val)","description": "(val)","fileName": "(val)","document": "(val)","mimeType": "(val)","newUpdateTimestamp": "(val)","newUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedule Attachments [POST /research-sys/api/v1/committee-schedule-attachments/]

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
            
### Delete Committee Schedule Attachments by Key [DELETE /research-sys/api/v1/committee-schedule-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Attachments [DELETE /research-sys/api/v1/committee-schedule-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Attachments with Matching [DELETE /research-sys/api/v1/committee-schedule-attachments/]

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

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
