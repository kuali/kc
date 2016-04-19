## Kew Notes [/research-sys/api/v1/kew-notes/]

### Get Kew Notes by Key [GET /research-sys/api/v1/kew-notes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Kew Notes [GET /research-sys/api/v1/kew-notes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kew Notes with Filtering [GET /research-sys/api/v1/kew-notes/]
    
+ Parameters

    + noteId (optional) - 
    + documentId (optional) - 
    + noteAuthorWorkflowId (optional) - 
    + noteCreateDate (optional) - 
    + noteText (optional) - 
    + lockVerNbr (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kew Notes [GET /research-sys/api/v1/kew-notes/]
	                                          
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
    
            {"columns":["noteId","documentId","noteAuthorWorkflowId","noteCreateDate","noteText","lockVerNbr"],"primaryKey":"noteId"}
		
### Get Blueprint API specification for Kew Notes [GET /research-sys/api/v1/kew-notes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kew Notes.md"
            transfer-encoding:chunked


### Update Kew Notes [PUT /research-sys/api/v1/kew-notes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kew Notes [PUT /research-sys/api/v1/kew-notes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kew Notes [POST /research-sys/api/v1/kew-notes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kew Notes [POST /research-sys/api/v1/kew-notes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"noteId": "(val)","documentId": "(val)","noteAuthorWorkflowId": "(val)","noteCreateDate": "(val)","noteText": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kew Notes by Key [DELETE /research-sys/api/v1/kew-notes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Notes [DELETE /research-sys/api/v1/kew-notes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Notes with Matching [DELETE /research-sys/api/v1/kew-notes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + noteId (optional) - 
    + documentId (optional) - 
    + noteAuthorWorkflowId (optional) - 
    + noteCreateDate (optional) - 
    + noteText (optional) - 
    + lockVerNbr (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
