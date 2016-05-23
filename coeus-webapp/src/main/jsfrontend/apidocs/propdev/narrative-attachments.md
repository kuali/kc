## Narrative Attachments [/propdev/api/v1/narrative-attachments/]

### Get Narrative Attachments by Key [GET /propdev/api/v1/narrative-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}

### Get All Narrative Attachments [GET /propdev/api/v1/narrative-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"},
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Narrative Attachments with Filtering [GET /propdev/api/v1/narrative-attachments/]
    
+ Parameters

    + uploadTimestamp (optional) - Upload Timestamp.
    + name (optional) - File Name. Maximum length is 150.
    + type (optional) - Type.
    + fileDataId (optional) - File Data Id.
    + uploadUser (optional) - Upload User.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"},
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Narrative Attachments [GET /propdev/api/v1/narrative-attachments/]
	                                          
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
    
            {"columns":["uploadTimestamp","name","type","fileDataId","uploadUser"],"primaryKey":"narrative"}
		
### Get Blueprint API specification for Narrative Attachments [GET /propdev/api/v1/narrative-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narrative Attachments.md"
            transfer-encoding:chunked
### Update Narrative Attachments [PUT /propdev/api/v1/narrative-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narrative Attachments [PUT /propdev/api/v1/narrative-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"},
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Narrative Attachments [POST /propdev/api/v1/narrative-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narrative Attachments [POST /propdev/api/v1/narrative-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"},
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"},
              {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            ]
### Delete Narrative Attachments by Key [DELETE /propdev/api/v1/narrative-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Attachments [DELETE /propdev/api/v1/narrative-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Attachments with Matching [DELETE /propdev/api/v1/narrative-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + uploadTimestamp (optional) - Upload Timestamp.
    + name (optional) - File Name. Maximum length is 150.
    + type (optional) - Type.
    + fileDataId (optional) - File Data Id.
    + uploadUser (optional) - Upload User.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
