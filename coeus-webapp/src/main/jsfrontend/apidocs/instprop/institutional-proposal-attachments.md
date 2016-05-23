## Institutional Proposal Attachments [/instprop/api/v1/institutional-proposal-attachments/]

### Get Institutional Proposal Attachments by Key [GET /instprop/api/v1/institutional-proposal-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Attachments [GET /instprop/api/v1/institutional-proposal-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Attachments with Filtering [GET /instprop/api/v1/institutional-proposal-attachments/]
    
+ Parameters

    + proposalAttachmentId (optional) - 
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + fileDataId (optional) - 
    + sequenceNumber (optional) - 
    + attachmentNumber (optional) - 
    + attachmentTitle (optional) - Description. Maximum length is 150.
    + attachmentTypeCode (optional) - Code. Maximum length is 3.
    + fileName (optional) - Id. Maximum length is 150.
    + contentType (optional) - 
    + comments (optional) - Comments. Maximum length is 2000.
    + documentStatusCode (optional) - 
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
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Attachments [GET /instprop/api/v1/institutional-proposal-attachments/]
	                                          
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
    
            {"columns":["proposalAttachmentId","proposalId","proposalNumber","fileDataId","sequenceNumber","attachmentNumber","attachmentTitle","attachmentTypeCode","fileName","contentType","comments","documentStatusCode","lastUpdateTimestamp","lastUpdateUser"],"primaryKey":"proposalAttachmentId"}
		
### Get Blueprint API specification for Institutional Proposal Attachments [GET /instprop/api/v1/institutional-proposal-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Attachments.md"
            transfer-encoding:chunked
### Update Institutional Proposal Attachments [PUT /instprop/api/v1/institutional-proposal-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Attachments [PUT /instprop/api/v1/institutional-proposal-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Institutional Proposal Attachments [POST /instprop/api/v1/institutional-proposal-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Attachments [POST /instprop/api/v1/institutional-proposal-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
### Delete Institutional Proposal Attachments by Key [DELETE /instprop/api/v1/institutional-proposal-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Attachments [DELETE /instprop/api/v1/institutional-proposal-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Attachments with Matching [DELETE /instprop/api/v1/institutional-proposal-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalAttachmentId (optional) - 
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + fileDataId (optional) - 
    + sequenceNumber (optional) - 
    + attachmentNumber (optional) - 
    + attachmentTitle (optional) - Description. Maximum length is 150.
    + attachmentTypeCode (optional) - Code. Maximum length is 3.
    + fileName (optional) - Id. Maximum length is 150.
    + contentType (optional) - 
    + comments (optional) - Comments. Maximum length is 2000.
    + documentStatusCode (optional) - 
    + lastUpdateTimestamp (optional) - The creation or last modification timestamp. Maximum length is 21.
    + lastUpdateUser (optional) - The user who created or last modified the object. Maximum length is 60.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
