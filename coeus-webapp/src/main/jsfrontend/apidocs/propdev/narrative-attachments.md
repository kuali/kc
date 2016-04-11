## Narrative Attachments [/research-sys/api/v1/narrative-attachments/]

### Get Narrative Attachments by Key [GET /research-sys/api/v1/narrative-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}

### Get All Narrative Attachments [GET /research-sys/api/v1/narrative-attachments/]
	 
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

### Get All Narrative Attachments with Filtering [GET /research-sys/api/v1/narrative-attachments/]
    
+ Parameters

        + uploadTimestamp
            + name
            + type
            + fileDataId
            + uploadUser

            
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
			
### Get Schema for Narrative Attachments [GET /research-sys/api/v1/narrative-attachments/]
	                                          
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
		
### Get Blueprint API specification for Narrative Attachments [GET /research-sys/api/v1/narrative-attachments/]
	 
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


### Update Narrative Attachments [PUT /research-sys/api/v1/narrative-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narrative Attachments [PUT /research-sys/api/v1/narrative-attachments/]

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

### Insert Narrative Attachments [POST /research-sys/api/v1/narrative-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"uploadTimestamp": "(val)","name": "(val)","type": "(val)","fileDataId": "(val)","uploadUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narrative Attachments [POST /research-sys/api/v1/narrative-attachments/]

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
            
### Delete Narrative Attachments by Key [DELETE /research-sys/api/v1/narrative-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Attachments [DELETE /research-sys/api/v1/narrative-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Attachments with Matching [DELETE /research-sys/api/v1/narrative-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + uploadTimestamp
            + name
            + type
            + fileDataId
            + uploadUser

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
