## Session Documents [/research-sys/api/v1/session-documents/]

### Get Session Documents by Key [GET /research-sys/api/v1/session-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}

### Get All Session Documents [GET /research-sys/api/v1/session-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"},
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
            ]

### Get All Session Documents with Filtering [GET /research-sys/api/v1/session-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + sessionId
            + documentNumber
            + principalId
            + ipAddress
            + lastUpdatedDate
            + serializedDocumentForm
            + encrypted
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"},
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Session Documents [GET /research-sys/api/v1/session-documents/]
	 
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
		
### Get Blueprint API specification for Session Documents [GET /research-sys/api/v1/session-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Session Documents.md"
            transfer-encoding:chunked


### Update Session Documents [PUT /research-sys/api/v1/session-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Session Documents [PUT /research-sys/api/v1/session-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"},
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Session Documents [POST /research-sys/api/v1/session-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Session Documents [POST /research-sys/api/v1/session-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"},
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"},
              {"sessionId": "(val)","documentNumber": "(val)","principalId": "(val)","ipAddress": "(val)","lastUpdatedDate": "(val)","serializedDocumentForm": "(val)","encrypted": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Session Documents by Key [DELETE /research-sys/api/v1/session-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Session Documents [DELETE /research-sys/api/v1/session-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Session Documents with Matching [DELETE /research-sys/api/v1/session-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + sessionId
            + documentNumber
            + principalId
            + ipAddress
            + lastUpdatedDate
            + serializedDocumentForm
            + encrypted


+ Response 204
