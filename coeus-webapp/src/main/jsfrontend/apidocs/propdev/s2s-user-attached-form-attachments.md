## S2s User Attached Form Attachments [/research-sys/api/v1/s2s-user-attached-form-attachments/]

### Get S2s User Attached Form Attachments by Key [GET /research-sys/api/v1/s2s-user-attached-form-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}

### Get All S2s User Attached Form Attachments [GET /research-sys/api/v1/s2s-user-attached-form-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s User Attached Form Attachments with Filtering [GET /research-sys/api/v1/s2s-user-attached-form-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + proposalNumber
            + type
            + name
            + contentId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s User Attached Form Attachments [GET /research-sys/api/v1/s2s-user-attached-form-attachments/]
	 
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
		
### Get Blueprint API specification for S2s User Attached Form Attachments [GET /research-sys/api/v1/s2s-user-attached-form-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s User Attached Form Attachments.md"
            transfer-encoding:chunked


### Update S2s User Attached Form Attachments [PUT /research-sys/api/v1/s2s-user-attached-form-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s User Attached Form Attachments [PUT /research-sys/api/v1/s2s-user-attached-form-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s User Attached Form Attachments [POST /research-sys/api/v1/s2s-user-attached-form-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s User Attached Form Attachments [POST /research-sys/api/v1/s2s-user-attached-form-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s User Attached Form Attachments by Key [DELETE /research-sys/api/v1/s2s-user-attached-form-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Attachments [DELETE /research-sys/api/v1/s2s-user-attached-form-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All S2s User Attached Form Attachments with Matching [DELETE /research-sys/api/v1/s2s-user-attached-form-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + proposalNumber
            + type
            + name
            + contentId


+ Response 204
