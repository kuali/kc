## Iacuc Protocol Personnel Attachments [/iacuc/api/v1/iacuc-protocol-personnel-attachments/]

### Get Iacuc Protocol Personnel Attachments by Key [GET /iacuc/api/v1/iacuc-protocol-personnel-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Personnel Attachments [GET /iacuc/api/v1/iacuc-protocol-personnel-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Personnel Attachments with Filtering [GET /iacuc/api/v1/iacuc-protocol-personnel-attachments/]
    
+ Parameters

    + id (optional) - Attachment Id. Maximum length is 12.
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + typeCode (optional) - Attachment Type. Maximum length is 3.
    + documentId (optional) - Document Id. Maximum length is 4.
    + fileId (optional) - File Name. Maximum length is 12.
    + description (optional) - Description. Maximum length is 200.
    + personId (optional) - Person. Maximum length is 12.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Personnel Attachments [GET /iacuc/api/v1/iacuc-protocol-personnel-attachments/]
	                                          
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
    
            {"columns":["id","protocolId","protocolNumber","sequenceNumber","typeCode","documentId","fileId","description","personId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Protocol Personnel Attachments [GET /iacuc/api/v1/iacuc-protocol-personnel-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Personnel Attachments.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Personnel Attachments [PUT /iacuc/api/v1/iacuc-protocol-personnel-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Personnel Attachments [PUT /iacuc/api/v1/iacuc-protocol-personnel-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Personnel Attachments [POST /iacuc/api/v1/iacuc-protocol-personnel-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Personnel Attachments [POST /iacuc/api/v1/iacuc-protocol-personnel-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Personnel Attachments by Key [DELETE /iacuc/api/v1/iacuc-protocol-personnel-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Personnel Attachments [DELETE /iacuc/api/v1/iacuc-protocol-personnel-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Personnel Attachments with Matching [DELETE /iacuc/api/v1/iacuc-protocol-personnel-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Attachment Id. Maximum length is 12.
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + typeCode (optional) - Attachment Type. Maximum length is 3.
    + documentId (optional) - Document Id. Maximum length is 4.
    + fileId (optional) - File Name. Maximum length is 12.
    + description (optional) - Description. Maximum length is 200.
    + personId (optional) - Person. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
