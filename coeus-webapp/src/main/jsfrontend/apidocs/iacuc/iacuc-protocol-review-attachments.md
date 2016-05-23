## Iacuc Protocol Review Attachments [/iacuc/api/v1/iacuc-protocol-review-attachments/]

### Get Iacuc Protocol Review Attachments by Key [GET /iacuc/api/v1/iacuc-protocol-review-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Review Attachments [GET /iacuc/api/v1/iacuc-protocol-review-attachments/]
	 
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

### Get All Iacuc Protocol Review Attachments with Filtering [GET /iacuc/api/v1/iacuc-protocol-review-attachments/]
    
+ Parameters

    + reviewerAttachmentId (optional) - Id. Maximum length is 10.
    + protocolOnlineReviewIdFk (optional) - Id. Maximum length is 10.
    + protocolIdFk (optional) - Protocol Id Fk. Maximum length is 12.
    + submissionIdFk (optional) - Submission Id Fk. Maximum length is 12.
    + attachmentId (optional) - 
    + description (optional) - Description. Maximum length is 200.
    + fileId (optional) - File Name. Maximum length is 12.
    + personId (optional) - Person Id. Maximum length is 40.
    + createUser (optional) - Create User. Maximum length is 80.
    + createTimestamp (optional) - Created Time. Maximum length is 21.
    + privateFlag (optional) - Private Attachment Flag. Maximum length is 1.
    + protocolPersonCanView (optional) - Protocol personnel can view Flag. Maximum length is 1.

            
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
			
### Get Schema for Iacuc Protocol Review Attachments [GET /iacuc/api/v1/iacuc-protocol-review-attachments/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Review Attachments [GET /iacuc/api/v1/iacuc-protocol-review-attachments/]
	 
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
### Update Iacuc Protocol Review Attachments [PUT /iacuc/api/v1/iacuc-protocol-review-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Review Attachments [PUT /iacuc/api/v1/iacuc-protocol-review-attachments/]

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
### Insert Iacuc Protocol Review Attachments [POST /iacuc/api/v1/iacuc-protocol-review-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reviewerAttachmentId": "(val)","protocolOnlineReviewIdFk": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","attachmentId": "(val)","description": "(val)","fileId": "(val)","personId": "(val)","createUser": "(val)","createTimestamp": "(val)","privateFlag": "(val)","protocolPersonCanView": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Review Attachments [POST /iacuc/api/v1/iacuc-protocol-review-attachments/]

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
### Delete Iacuc Protocol Review Attachments by Key [DELETE /iacuc/api/v1/iacuc-protocol-review-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Attachments [DELETE /iacuc/api/v1/iacuc-protocol-review-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Attachments with Matching [DELETE /iacuc/api/v1/iacuc-protocol-review-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reviewerAttachmentId (optional) - Id. Maximum length is 10.
    + protocolOnlineReviewIdFk (optional) - Id. Maximum length is 10.
    + protocolIdFk (optional) - Protocol Id Fk. Maximum length is 12.
    + submissionIdFk (optional) - Submission Id Fk. Maximum length is 12.
    + attachmentId (optional) - 
    + description (optional) - Description. Maximum length is 200.
    + fileId (optional) - File Name. Maximum length is 12.
    + personId (optional) - Person Id. Maximum length is 40.
    + createUser (optional) - Create User. Maximum length is 80.
    + createTimestamp (optional) - Created Time. Maximum length is 21.
    + privateFlag (optional) - Private Attachment Flag. Maximum length is 1.
    + protocolPersonCanView (optional) - Protocol personnel can view Flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
