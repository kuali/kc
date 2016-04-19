## Negotiation Activity Attachments [/negotiation/api/v1/negotiation-activity-attachments/]

### Get Negotiation Activity Attachments by Key [GET /negotiation/api/v1/negotiation-activity-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Activity Attachments [GET /negotiation/api/v1/negotiation-activity-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Activity Attachments with Filtering [GET /negotiation/api/v1/negotiation-activity-attachments/]
    
+ Parameters

    + attachmentId (optional) - attachmentId. Maximum length is 22.
    + activityId (optional) - 
    + fileId (optional) - 
    + description (optional) - Attachment Description. Maximum length is 200.
    + restricted (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Activity Attachments [GET /negotiation/api/v1/negotiation-activity-attachments/]
	                                          
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
    
            {"columns":["attachmentId","activityId","fileId","description","restricted"],"primaryKey":"attachmentId"}
		
### Get Blueprint API specification for Negotiation Activity Attachments [GET /negotiation/api/v1/negotiation-activity-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Activity Attachments.md"
            transfer-encoding:chunked


### Update Negotiation Activity Attachments [PUT /negotiation/api/v1/negotiation-activity-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Activity Attachments [PUT /negotiation/api/v1/negotiation-activity-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Activity Attachments [POST /negotiation/api/v1/negotiation-activity-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Activity Attachments [POST /negotiation/api/v1/negotiation-activity-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","activityId": "(val)","fileId": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Activity Attachments by Key [DELETE /negotiation/api/v1/negotiation-activity-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activity Attachments [DELETE /negotiation/api/v1/negotiation-activity-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activity Attachments with Matching [DELETE /negotiation/api/v1/negotiation-activity-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + attachmentId (optional) - attachmentId. Maximum length is 22.
    + activityId (optional) - 
    + fileId (optional) - 
    + description (optional) - Attachment Description. Maximum length is 200.
    + restricted (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
