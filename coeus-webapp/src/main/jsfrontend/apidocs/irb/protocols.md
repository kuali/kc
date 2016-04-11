## Protocols [/research-sys/api/v1/protocols/]

### Get Protocols by Key [GET /research-sys/api/v1/protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Protocols [GET /research-sys/api/v1/protocols/]
	 
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

### Get All Protocols with Filtering [GET /research-sys/api/v1/protocols/]
    
+ Parameters

        + protocolId
            + documentNumber
            + protocolNumber
            + sequenceNumber
            + active
            + protocolTypeCode
            + protocolStatusCode
            + title
            + description
            + initialSubmissionDate
            + approvalDate
            + expirationDate
            + lastApprovalDate
            + fdaApplicationNumber
            + referenceNumber1
            + referenceNumber2
            + createTimestamp
            + createUser

            
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
			
### Get Schema for Protocols [GET /research-sys/api/v1/protocols/]
	                                          
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
		
### Get Blueprint API specification for Protocols [GET /research-sys/api/v1/protocols/]
	 
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


### Update Protocols [PUT /research-sys/api/v1/protocols/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocols [PUT /research-sys/api/v1/protocols/]

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

### Insert Protocols [POST /research-sys/api/v1/protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","lastApprovalDate": "(val)","fdaApplicationNumber": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocols [POST /research-sys/api/v1/protocols/]

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
            
### Delete Protocols by Key [DELETE /research-sys/api/v1/protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocols [DELETE /research-sys/api/v1/protocols/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocols with Matching [DELETE /research-sys/api/v1/protocols/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolId
            + documentNumber
            + protocolNumber
            + sequenceNumber
            + active
            + protocolTypeCode
            + protocolStatusCode
            + title
            + description
            + initialSubmissionDate
            + approvalDate
            + expirationDate
            + lastApprovalDate
            + fdaApplicationNumber
            + referenceNumber1
            + referenceNumber2
            + createTimestamp
            + createUser

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
