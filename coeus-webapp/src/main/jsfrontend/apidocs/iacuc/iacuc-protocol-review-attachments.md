## Iacuc Protocol Review Attachments [/research-sys/api/v1/iacuc-protocol-review-attachments/]

### Get Iacuc Protocol Review Attachments by Key [GET /research-sys/api/v1/iacuc-protocol-review-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Review Attachments [GET /research-sys/api/v1/iacuc-protocol-review-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"},
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Review Attachments with Filtering [GET /research-sys/api/v1/iacuc-protocol-review-attachments/]
    
+ Parameters

        + reviewerAttachmentId
            + protocolOnlineReviewIdFk
            + protocolIdFk
            + submissionIdFk
            + attachmentId
            + description
            + fileId
            + personId
            + createUser
            + createTimestamp
            + privateFlag
            + protocolPersonCanView

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"},
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Review Attachments [GET /research-sys/api/v1/iacuc-protocol-review-attachments/]
	                                          
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
    
            {"columns":["reviewerAttachmentId","protocolOnlineReviewIdFk","protocolIdFk","submissionIdFk","attachmentId","description","fileId","personId","createUser","createTimestamp","privateFlag","protocolPersonCanView"],"primaryKey":"reviewerAttachmentId"}
		
### Get Blueprint API specification for Iacuc Protocol Review Attachments [GET /research-sys/api/v1/iacuc-protocol-review-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Review Attachments.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Review Attachments [PUT /research-sys/api/v1/iacuc-protocol-review-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Review Attachments [PUT /research-sys/api/v1/iacuc-protocol-review-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"},
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Review Attachments [POST /research-sys/api/v1/iacuc-protocol-review-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Review Attachments [POST /research-sys/api/v1/iacuc-protocol-review-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"},
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"},
              {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Review Attachments by Key [DELETE /research-sys/api/v1/iacuc-protocol-review-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Attachments [DELETE /research-sys/api/v1/iacuc-protocol-review-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Attachments with Matching [DELETE /research-sys/api/v1/iacuc-protocol-review-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + reviewerAttachmentId
            + protocolOnlineReviewIdFk
            + protocolIdFk
            + submissionIdFk
            + attachmentId
            + description
            + fileId
            + personId
            + createUser
            + createTimestamp
            + privateFlag
            + protocolPersonCanView

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
