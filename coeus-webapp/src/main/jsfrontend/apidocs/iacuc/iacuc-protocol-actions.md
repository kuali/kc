## Iacuc Protocol Actions [/research-sys/api/v1/iacuc-protocol-actions/]

### Get Iacuc Protocol Actions by Key [GET /research-sys/api/v1/iacuc-protocol-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Actions [GET /research-sys/api/v1/iacuc-protocol-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Actions with Filtering [GET /research-sys/api/v1/iacuc-protocol-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolActionId
            + protocolNumber
            + sequenceNumber
            + actionId
            + protocolActionTypeCode
            + submissionNumber
            + comments
            + createTimestamp
            + createUser
            + actionDate
            + protocolId
            + submissionIdFk
            + actualActionDate
            + prevSubmissionStatusCode
            + submissionTypeCode
            + prevProtocolStatusCode
            + followupActionCode
            + createdSubmission
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Actions [GET /research-sys/api/v1/iacuc-protocol-actions/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Actions [GET /research-sys/api/v1/iacuc-protocol-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Actions.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Actions [PUT /research-sys/api/v1/iacuc-protocol-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Actions [PUT /research-sys/api/v1/iacuc-protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Actions [POST /research-sys/api/v1/iacuc-protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Actions [POST /research-sys/api/v1/iacuc-protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Actions by Key [DELETE /research-sys/api/v1/iacuc-protocol-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Actions [DELETE /research-sys/api/v1/iacuc-protocol-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Actions with Matching [DELETE /research-sys/api/v1/iacuc-protocol-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolActionId
            + protocolNumber
            + sequenceNumber
            + actionId
            + protocolActionTypeCode
            + submissionNumber
            + comments
            + createTimestamp
            + createUser
            + actionDate
            + protocolId
            + submissionIdFk
            + actualActionDate
            + prevSubmissionStatusCode
            + submissionTypeCode
            + prevProtocolStatusCode
            + followupActionCode
            + createdSubmission


+ Response 204
