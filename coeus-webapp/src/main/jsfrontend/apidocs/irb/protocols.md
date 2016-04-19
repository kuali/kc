## Protocols [/irb/api/v1/protocols/]

### Get Protocols by Key [GET /irb/api/v1/protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Protocols [GET /irb/api/v1/protocols/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocols with Filtering [GET /irb/api/v1/protocols/]
    
+ Parameters

    + protocolId (optional) - Protocol Id. Maximum length is 12.
    + documentNumber (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + active (optional) - Active. Maximum length is 1.
    + protocolTypeCode (optional) - Protocol Type Code. Maximum length is 3.
    + protocolStatusCode (optional) - Protocol Status Code. Maximum length is 3.
    + title (optional) - Title. Maximum length is 2000.
    + description (optional) - Description. Maximum length is 2000.
    + initialSubmissionDate (optional) - Initial Submission Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.
    + lastApprovalDate (optional) - Last Approval Date. Maximum length is 10.
    + fdaApplicationNumber (optional) - Fda Application Number. Maximum length is 15.
    + referenceNumber1 (optional) - Reference Number 1. Maximum length is 50.
    + referenceNumber2 (optional) - Reference Number 2. Maximum length is 50.
    + createTimestamp (optional) - 
    + createUser (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocols [GET /irb/api/v1/protocols/]
	                                          
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
    
            {"columns":["protocolId","documentNumber","protocolNumber","sequenceNumber","active","protocolTypeCode","protocolStatusCode","title","description","initialSubmissionDate","approvalDate","expirationDate","lastApprovalDate","fdaApplicationNumber","referenceNumber1","referenceNumber2","createTimestamp","createUser"],"primaryKey":"protocolId"}
		
### Get Blueprint API specification for Protocols [GET /irb/api/v1/protocols/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocols.md"
            transfer-encoding:chunked


### Update Protocols [PUT /irb/api/v1/protocols/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocols [PUT /irb/api/v1/protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocols [POST /irb/api/v1/protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocols [POST /irb/api/v1/protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocols by Key [DELETE /irb/api/v1/protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocols [DELETE /irb/api/v1/protocols/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocols with Matching [DELETE /irb/api/v1/protocols/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolId (optional) - Protocol Id. Maximum length is 12.
    + documentNumber (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + active (optional) - Active. Maximum length is 1.
    + protocolTypeCode (optional) - Protocol Type Code. Maximum length is 3.
    + protocolStatusCode (optional) - Protocol Status Code. Maximum length is 3.
    + title (optional) - Title. Maximum length is 2000.
    + description (optional) - Description. Maximum length is 2000.
    + initialSubmissionDate (optional) - Initial Submission Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.
    + lastApprovalDate (optional) - Last Approval Date. Maximum length is 10.
    + fdaApplicationNumber (optional) - Fda Application Number. Maximum length is 15.
    + referenceNumber1 (optional) - Reference Number 1. Maximum length is 50.
    + referenceNumber2 (optional) - Reference Number 2. Maximum length is 50.
    + createTimestamp (optional) - 
    + createUser (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
