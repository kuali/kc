## Attachment Files [/research-common/api/v1/attachment-files/]

### Get Attachment Files by Key [GET /research-common/api/v1/attachment-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}

### Get All Attachment Files [GET /research-common/api/v1/attachment-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Attachment Files with Filtering [GET /research-common/api/v1/attachment-files/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 12.
    + sequenceNumber (optional) - 
    + name (optional) - File Name. Maximum length is 150.
    + type (optional) - Type. Maximum length is 250.
    + data (optional) - 
    + fileDataId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Attachment Files [GET /research-common/api/v1/attachment-files/]
	                                          
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
    
            {"columns":["id","sequenceNumber","name","type","data","fileDataId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Attachment Files [GET /research-common/api/v1/attachment-files/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Attachment Files.md"
            transfer-encoding:chunked
### Update Attachment Files [PUT /research-common/api/v1/attachment-files/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Attachment Files [PUT /research-common/api/v1/attachment-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Attachment Files [POST /research-common/api/v1/attachment-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Attachment Files [POST /research-common/api/v1/attachment-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","name": "(val)","type": "(val)","data": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Attachment Files by Key [DELETE /research-common/api/v1/attachment-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachment Files [DELETE /research-common/api/v1/attachment-files/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachment Files with Matching [DELETE /research-common/api/v1/attachment-files/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 12.
    + sequenceNumber (optional) - 
    + name (optional) - File Name. Maximum length is 150.
    + type (optional) - Type. Maximum length is 250.
    + data (optional) - 
    + fileDataId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
