## Institutional Proposal Attachments [/research-sys/api/v1/institutional-proposal-attachments/]

### Get Institutional Proposal Attachments by Key [GET /research-sys/api/v1/institutional-proposal-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Attachments [GET /research-sys/api/v1/institutional-proposal-attachments/]
	 
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

### Get All Institutional Proposal Attachments with Filtering [GET /research-sys/api/v1/institutional-proposal-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalAttachmentId
            + proposalId
            + proposalNumber
            + fileDataId
            + sequenceNumber
            + attachmentNumber
            + attachmentTitle
            + attachmentTypeCode
            + fileName
            + contentType
            + comments
            + documentStatusCode
            + lastUpdateTimestamp
            + lastUpdateUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Attachments [GET /research-sys/api/v1/institutional-proposal-attachments/]
	 
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
		
### Get Blueprint API specification for Institutional Proposal Attachments [GET /research-sys/api/v1/institutional-proposal-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Attachments.md"
            transfer-encoding:chunked


### Update Institutional Proposal Attachments [PUT /research-sys/api/v1/institutional-proposal-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Attachments [PUT /research-sys/api/v1/institutional-proposal-attachments/]

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

### Insert Institutional Proposal Attachments [POST /research-sys/api/v1/institutional-proposal-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalAttachmentId": "(val)","proposalId": "(val)","proposalNumber": "(val)","fileDataId": "(val)","sequenceNumber": "(val)","attachmentNumber": "(val)","attachmentTitle": "(val)","attachmentTypeCode": "(val)","fileName": "(val)","contentType": "(val)","comments": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Attachments [POST /research-sys/api/v1/institutional-proposal-attachments/]

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
            
### Delete Institutional Proposal Attachments by Key [DELETE /research-sys/api/v1/institutional-proposal-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Attachments [DELETE /research-sys/api/v1/institutional-proposal-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposal Attachments with Matching [DELETE /research-sys/api/v1/institutional-proposal-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalAttachmentId
            + proposalId
            + proposalNumber
            + fileDataId
            + sequenceNumber
            + attachmentNumber
            + attachmentTitle
            + attachmentTypeCode
            + fileName
            + contentType
            + comments
            + documentStatusCode
            + lastUpdateTimestamp
            + lastUpdateUser


+ Response 204
