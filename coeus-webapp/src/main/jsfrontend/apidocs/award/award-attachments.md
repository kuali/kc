## Award Attachments [/research-sys/api/v1/award-attachments/]

### Get Award Attachments by Key [GET /research-sys/api/v1/award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}

### Get All Award Attachments [GET /research-sys/api/v1/award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Attachments with Filtering [GET /research-sys/api/v1/award-attachments/]
    
+ Parameters

        + awardAttachmentId
            + awardId
            + awardNumber
            + sequenceNumber
            + typeCode
            + documentId
            + fileId
            + description
            + documentStatusCode
            + lastUpdateTimestamp
            + lastUpdateUser

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Attachments [GET /research-sys/api/v1/award-attachments/]
	                                          
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
    
            {"columns":["awardAttachmentId","awardId","awardNumber","sequenceNumber","typeCode","documentId","fileId","description","documentStatusCode","lastUpdateTimestamp","lastUpdateUser"],"primaryKey":"awardAttachmentId"}
		
### Get Blueprint API specification for Award Attachments [GET /research-sys/api/v1/award-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Attachments.md"
            transfer-encoding:chunked


### Update Award Attachments [PUT /research-sys/api/v1/award-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Attachments [PUT /research-sys/api/v1/award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Attachments [POST /research-sys/api/v1/award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Attachments [POST /research-sys/api/v1/award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"},
              {"awardAttachmentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","documentStatusCode": "(val)","lastUpdateTimestamp": "(val)","lastUpdateUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Attachments by Key [DELETE /research-sys/api/v1/award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Attachments [DELETE /research-sys/api/v1/award-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Attachments with Matching [DELETE /research-sys/api/v1/award-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardAttachmentId
            + awardId
            + awardNumber
            + sequenceNumber
            + typeCode
            + documentId
            + fileId
            + description
            + documentStatusCode
            + lastUpdateTimestamp
            + lastUpdateUser

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
