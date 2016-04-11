## Kew Attachments [/research-sys/api/v1/kew-attachments/]

### Get Kew Attachments by Key [GET /research-sys/api/v1/kew-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Kew Attachments [GET /research-sys/api/v1/kew-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kew Attachments with Filtering [GET /research-sys/api/v1/kew-attachments/]
    
+ Parameters

        + attachmentId
            + fileName
            + fileLoc
            + mimeType
            + lockVerNbr

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kew Attachments [GET /research-sys/api/v1/kew-attachments/]
	                                          
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
    
            {"columns":["attachmentId","fileName","fileLoc","mimeType","lockVerNbr"],"primaryKey":"attachmentId"}
		
### Get Blueprint API specification for Kew Attachments [GET /research-sys/api/v1/kew-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kew Attachments.md"
            transfer-encoding:chunked


### Update Kew Attachments [PUT /research-sys/api/v1/kew-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kew Attachments [PUT /research-sys/api/v1/kew-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kew Attachments [POST /research-sys/api/v1/kew-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kew Attachments [POST /research-sys/api/v1/kew-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"attachmentId": "(val)","fileName": "(val)","fileLoc": "(val)","mimeType": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kew Attachments by Key [DELETE /research-sys/api/v1/kew-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Attachments [DELETE /research-sys/api/v1/kew-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Attachments with Matching [DELETE /research-sys/api/v1/kew-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + attachmentId
            + fileName
            + fileLoc
            + mimeType
            + lockVerNbr

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
