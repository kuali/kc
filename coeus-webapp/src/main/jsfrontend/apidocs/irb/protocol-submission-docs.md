## Protocol Submission Docs [/irb/api/v1/protocol-submission-docs/]

### Get Protocol Submission Docs by Key [GET /irb/api/v1/protocol-submission-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}

### Get All Protocol Submission Docs [GET /irb/api/v1/protocol-submission-docs/]
	 
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

### Get All Protocol Submission Docs with Filtering [GET /irb/api/v1/protocol-submission-docs/]
    
+ Parameters

    + submissionDocId (optional) - Protocol Submission Doc Id. Maximum length is 22.
    + protocolId (optional) - 
    + submissionIdFk (optional) - Protocol Submission Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + submissionNumber (optional) - Submission Number. Maximum length is 22.
    + documentId (optional) - Document Id. Maximum length is 22.
    + fileName (optional) - File Name. Maximum length is 300.
    + contentType (optional) - 
    + description (optional) - Description. Maximum length is 200.
    + document (optional) - Document. Maximum length is 4000.

            
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
			
### Get Schema for Protocol Submission Docs [GET /irb/api/v1/protocol-submission-docs/]
	                                          
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
		
### Get Blueprint API specification for Protocol Submission Docs [GET /irb/api/v1/protocol-submission-docs/]
	 
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
### Update Protocol Submission Docs [PUT /irb/api/v1/protocol-submission-docs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Submission Docs [PUT /irb/api/v1/protocol-submission-docs/]

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
### Insert Protocol Submission Docs [POST /irb/api/v1/protocol-submission-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionDocId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","documentId": "(val)","fileName": "(val)","contentType": "(val)","description": "(val)","document": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Submission Docs [POST /irb/api/v1/protocol-submission-docs/]

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
### Delete Protocol Submission Docs by Key [DELETE /irb/api/v1/protocol-submission-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Docs [DELETE /irb/api/v1/protocol-submission-docs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Docs with Matching [DELETE /irb/api/v1/protocol-submission-docs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + submissionDocId (optional) - Protocol Submission Doc Id. Maximum length is 22.
    + protocolId (optional) - 
    + submissionIdFk (optional) - Protocol Submission Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + submissionNumber (optional) - Submission Number. Maximum length is 22.
    + documentId (optional) - Document Id. Maximum length is 22.
    + fileName (optional) - File Name. Maximum length is 300.
    + contentType (optional) - 
    + description (optional) - Description. Maximum length is 200.
    + document (optional) - Document. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
