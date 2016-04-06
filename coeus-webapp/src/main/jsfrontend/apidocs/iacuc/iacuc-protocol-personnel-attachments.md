## Iacuc Protocol Personnel Attachments [/research-sys/api/v1/iacuc-protocol-personnel-attachments/]

### Get Iacuc Protocol Personnel Attachments by Key [GET /research-sys/api/v1/iacuc-protocol-personnel-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Personnel Attachments [GET /research-sys/api/v1/iacuc-protocol-personnel-attachments/]
	 
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

### Get All Iacuc Protocol Personnel Attachments with Filtering [GET /research-sys/api/v1/iacuc-protocol-personnel-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + protocolId
            + protocolNumber
            + sequenceNumber
            + typeCode
            + documentId
            + fileId
            + description
            + personId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Personnel Attachments [GET /research-sys/api/v1/iacuc-protocol-personnel-attachments/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Personnel Attachments [GET /research-sys/api/v1/iacuc-protocol-personnel-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Personnel Attachments.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Personnel Attachments [PUT /research-sys/api/v1/iacuc-protocol-personnel-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Personnel Attachments [PUT /research-sys/api/v1/iacuc-protocol-personnel-attachments/]

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

### Insert Iacuc Protocol Personnel Attachments [POST /research-sys/api/v1/iacuc-protocol-personnel-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","typeCode": "(val)","documentId": "(val)","fileId": "(val)","description": "(val)","personId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Personnel Attachments [POST /research-sys/api/v1/iacuc-protocol-personnel-attachments/]

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
            
### Delete Iacuc Protocol Personnel Attachments by Key [DELETE /research-sys/api/v1/iacuc-protocol-personnel-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Personnel Attachments [DELETE /research-sys/api/v1/iacuc-protocol-personnel-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Personnel Attachments with Matching [DELETE /research-sys/api/v1/iacuc-protocol-personnel-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + protocolId
            + protocolNumber
            + sequenceNumber
            + typeCode
            + documentId
            + fileId
            + description
            + personId


+ Response 204
