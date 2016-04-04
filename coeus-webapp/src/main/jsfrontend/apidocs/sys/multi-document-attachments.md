## Multi Document Attachments [/research-sys/api/v1/multi-document-attachments/]

### Get Multi Document Attachments by Key [GET /research-sys/api/v1/multi-document-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Multi Document Attachments [GET /research-sys/api/v1/multi-document-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Multi Document Attachments with Filtering [GET /research-sys/api/v1/multi-document-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + documentNumber
            + fileName
            + attachmentContent
            + contentType
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Multi Document Attachments [GET /research-sys/api/v1/multi-document-attachments/]
	 
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
		
### Get Blueprint API specification for Multi Document Attachments [GET /research-sys/api/v1/multi-document-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Multi Document Attachments.md"
            transfer-encoding:chunked


### Update Multi Document Attachments [PUT /research-sys/api/v1/multi-document-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Multi Document Attachments [PUT /research-sys/api/v1/multi-document-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Multi Document Attachments [POST /research-sys/api/v1/multi-document-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Multi Document Attachments [POST /research-sys/api/v1/multi-document-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Multi Document Attachments by Key [DELETE /research-sys/api/v1/multi-document-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Multi Document Attachments [DELETE /research-sys/api/v1/multi-document-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Multi Document Attachments with Matching [DELETE /research-sys/api/v1/multi-document-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + documentNumber
            + fileName
            + attachmentContent
            + contentType


+ Response 204
