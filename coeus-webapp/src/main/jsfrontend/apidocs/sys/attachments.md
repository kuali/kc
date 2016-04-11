## Attachments [/research-sys/api/v1/attachments/]

### Get Attachments by Key [GET /research-sys/api/v1/attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Attachments [GET /research-sys/api/v1/attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Attachments with Filtering [GET /research-sys/api/v1/attachments/]
    
+ Parameters

        + noteIdentifier
            + attachmentMimeTypeCode
            + attachmentFileName
            + attachmentIdentifier
            + attachmentFileSize
            + attachmentTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Attachments [GET /research-sys/api/v1/attachments/]
	                                          
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
    
            {"columns":["noteIdentifier","attachmentMimeTypeCode","attachmentFileName","attachmentIdentifier","attachmentFileSize","attachmentTypeCode"],"primaryKey":"noteIdentifier"}
		
### Get Blueprint API specification for Attachments [GET /research-sys/api/v1/attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Attachments.md"
            transfer-encoding:chunked


### Update Attachments [PUT /research-sys/api/v1/attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Attachments [PUT /research-sys/api/v1/attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Attachments [POST /research-sys/api/v1/attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Attachments [POST /research-sys/api/v1/attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","attachmentMimeTypeCode": "(val)","attachmentFileName": "(val)","attachmentIdentifier": "(val)","attachmentFileSize": "(val)","attachmentTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Attachments by Key [DELETE /research-sys/api/v1/attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachments [DELETE /research-sys/api/v1/attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachments with Matching [DELETE /research-sys/api/v1/attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + noteIdentifier
            + attachmentMimeTypeCode
            + attachmentFileName
            + attachmentIdentifier
            + attachmentFileSize
            + attachmentTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
