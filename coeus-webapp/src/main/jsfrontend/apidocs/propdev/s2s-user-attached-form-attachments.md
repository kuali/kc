## S2s User Attached Form Attachments [/propdev/api/v1/s2s-user-attached-form-attachments/]

### Get S2s User Attached Form Attachments by Key [GET /propdev/api/v1/s2s-user-attached-form-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}

### Get All S2s User Attached Form Attachments [GET /propdev/api/v1/s2s-user-attached-form-attachments/]
	 
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

### Get All S2s User Attached Form Attachments with Filtering [GET /propdev/api/v1/s2s-user-attached-form-attachments/]
    
+ Parameters

    + id (optional) - 
    + proposalNumber (optional) - 
    + type (optional) - 
    + name (optional) - 
    + contentId (optional) - 

            
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
			
### Get Schema for S2s User Attached Form Attachments [GET /propdev/api/v1/s2s-user-attached-form-attachments/]
	                                          
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
    
            {"columns":["id","proposalNumber","type","name","contentId"],"primaryKey":"id"}
		
### Get Blueprint API specification for S2s User Attached Form Attachments [GET /propdev/api/v1/s2s-user-attached-form-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s User Attached Form Attachments.md"
            transfer-encoding:chunked
### Update S2s User Attached Form Attachments [PUT /propdev/api/v1/s2s-user-attached-form-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s User Attached Form Attachments [PUT /propdev/api/v1/s2s-user-attached-form-attachments/]

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
### Insert S2s User Attached Form Attachments [POST /propdev/api/v1/s2s-user-attached-form-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","proposalNumber": "(val)","type": "(val)","name": "(val)","contentId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s User Attached Form Attachments [POST /propdev/api/v1/s2s-user-attached-form-attachments/]

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
### Delete S2s User Attached Form Attachments by Key [DELETE /propdev/api/v1/s2s-user-attached-form-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Attachments [DELETE /propdev/api/v1/s2s-user-attached-form-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Attachments with Matching [DELETE /propdev/api/v1/s2s-user-attached-form-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + proposalNumber (optional) - 
    + type (optional) - 
    + name (optional) - 
    + contentId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
