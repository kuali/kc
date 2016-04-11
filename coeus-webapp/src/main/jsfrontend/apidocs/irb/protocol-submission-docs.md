## Protocol Submission Docs [/research-sys/api/v1/protocol-submission-docs/]

### Get Protocol Submission Docs by Key [GET /research-sys/api/v1/protocol-submission-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}

### Get All Protocol Submission Docs [GET /research-sys/api/v1/protocol-submission-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"},
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Submission Docs with Filtering [GET /research-sys/api/v1/protocol-submission-docs/]
    
+ Parameters

        + submissionDocId
            + protocolId
            + submissionIdFk
            + protocolNumber
            + sequenceNumber
            + submissionNumber
            + documentId
            + fileName
            + contentType
            + description
            + document

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"},
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Submission Docs [GET /research-sys/api/v1/protocol-submission-docs/]
	                                          
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
    
            {"columns":["submissionDocId","protocolId","submissionIdFk","protocolNumber","sequenceNumber","submissionNumber","documentId","fileName","contentType","description","document"],"primaryKey":"submissionDocId"}
		
### Get Blueprint API specification for Protocol Submission Docs [GET /research-sys/api/v1/protocol-submission-docs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Submission Docs.md"
            transfer-encoding:chunked


### Update Protocol Submission Docs [PUT /research-sys/api/v1/protocol-submission-docs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Submission Docs [PUT /research-sys/api/v1/protocol-submission-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"},
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Submission Docs [POST /research-sys/api/v1/protocol-submission-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Submission Docs [POST /research-sys/api/v1/protocol-submission-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"},
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"},
              {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Submission Docs by Key [DELETE /research-sys/api/v1/protocol-submission-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Docs [DELETE /research-sys/api/v1/protocol-submission-docs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Docs with Matching [DELETE /research-sys/api/v1/protocol-submission-docs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + submissionDocId
            + protocolId
            + submissionIdFk
            + protocolNumber
            + sequenceNumber
            + submissionNumber
            + documentId
            + fileName
            + contentType
            + description
            + document

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
