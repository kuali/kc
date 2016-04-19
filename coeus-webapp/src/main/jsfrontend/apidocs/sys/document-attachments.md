## Document Attachments [/research-sys/api/v1/document-attachments/]

### Get Document Attachments by Key [GET /research-sys/api/v1/document-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Document Attachments [GET /research-sys/api/v1/document-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Attachments with Filtering [GET /research-sys/api/v1/document-attachments/]
    
+ Parameters

    + documentNumber (optional) - 
    + fileName (optional) - 
    + attachmentContent (optional) - 
    + contentType (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Attachments [GET /research-sys/api/v1/document-attachments/]
	                                          
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
    
            {"columns":["documentNumber","fileName","attachmentContent","contentType"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Document Attachments [GET /research-sys/api/v1/document-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Attachments.md"
            transfer-encoding:chunked


### Update Document Attachments [PUT /research-sys/api/v1/document-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Attachments [PUT /research-sys/api/v1/document-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Attachments [POST /research-sys/api/v1/document-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Attachments [POST /research-sys/api/v1/document-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","fileName": "(val)","attachmentContent": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Attachments by Key [DELETE /research-sys/api/v1/document-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Attachments [DELETE /research-sys/api/v1/document-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Attachments with Matching [DELETE /research-sys/api/v1/document-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + documentNumber (optional) - 
    + fileName (optional) - 
    + attachmentContent (optional) - 
    + contentType (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
