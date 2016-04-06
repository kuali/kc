## Attachment Files [/research-sys/api/v1/attachment-files/]

### Get Attachment Files by Key [GET /research-sys/api/v1/attachment-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}

### Get All Attachment Files [GET /research-sys/api/v1/attachment-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
            ]

### Get All Attachment Files with Filtering [GET /research-sys/api/v1/attachment-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + sequenceNumber
            + name
            + type
            + data
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Attachment Files [GET /research-sys/api/v1/attachment-files/]
	 
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
		
### Get Blueprint API specification for Attachment Files [GET /research-sys/api/v1/attachment-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Attachment Files.md"
            transfer-encoding:chunked


### Update Attachment Files [PUT /research-sys/api/v1/attachment-files/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Attachment Files [PUT /research-sys/api/v1/attachment-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Attachment Files [POST /research-sys/api/v1/attachment-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Attachment Files [POST /research-sys/api/v1/attachment-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Attachment Files by Key [DELETE /research-sys/api/v1/attachment-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachment Files [DELETE /research-sys/api/v1/attachment-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Attachment Files with Matching [DELETE /research-sys/api/v1/attachment-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + sequenceNumber
            + name
            + type
            + data


+ Response 204
