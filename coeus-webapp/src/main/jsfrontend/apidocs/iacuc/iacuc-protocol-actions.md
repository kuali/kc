## Iacuc Protocol Actions [/iacuc/api/v1/iacuc-protocol-actions/]

### Get Iacuc Protocol Actions by Key [GET /iacuc/api/v1/iacuc-protocol-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Actions [GET /iacuc/api/v1/iacuc-protocol-actions/]
	 
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

### Get All Iacuc Protocol Actions with Filtering [GET /iacuc/api/v1/iacuc-protocol-actions/]
    
+ Parameters

    + protocolActionId (optional) - Ac Protocol Actions Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + actionId (optional) - Action Id. Maximum length is 22.
    + protocolActionTypeCode (optional) - Protocol Action Type Code. Maximum length is 22.
    + submissionNumber (optional) - Submission Number. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 2000.
    + createTimestamp (optional) - Create Timestamp. Maximum length is 10.
    + createUser (optional) - Create User. Maximum length is 60.
    + actionDate (optional) - Action Date. Maximum length is 10.
    + protocolId (optional) - 
    + submissionIdFk (optional) - 
    + actualActionDate (optional) - 
    + prevSubmissionStatusCode (optional) - 
    + submissionTypeCode (optional) - 
    + prevProtocolStatusCode (optional) - 
    + followupActionCode (optional) - 
    + createdSubmission (optional) - 

            
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
			
### Get Schema for Iacuc Protocol Actions [GET /iacuc/api/v1/iacuc-protocol-actions/]
	                                          
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
    
            {"columns":["protocolActionId","protocolNumber","sequenceNumber","actionId","protocolActionTypeCode","submissionNumber","comments","createTimestamp","createUser","actionDate","protocolId","submissionIdFk","actualActionDate","prevSubmissionStatusCode","submissionTypeCode","prevProtocolStatusCode","followupActionCode","createdSubmission"],"primaryKey":"protocolActionId"}
		
### Get Blueprint API specification for Iacuc Protocol Actions [GET /iacuc/api/v1/iacuc-protocol-actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Actions.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Actions [PUT /iacuc/api/v1/iacuc-protocol-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Actions [PUT /iacuc/api/v1/iacuc-protocol-actions/]

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

### Insert Iacuc Protocol Actions [POST /iacuc/api/v1/iacuc-protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolActionId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","protocolActionTypeCode": "(val)","submissionNumber": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","protocolId": "(val)","submissionIdFk": "(val)","actualActionDate": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","followupActionCode": "(val)","createdSubmission": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Actions [POST /iacuc/api/v1/iacuc-protocol-actions/]

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
            
### Delete Iacuc Protocol Actions by Key [DELETE /iacuc/api/v1/iacuc-protocol-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Actions [DELETE /iacuc/api/v1/iacuc-protocol-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Actions with Matching [DELETE /iacuc/api/v1/iacuc-protocol-actions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolActionId (optional) - Ac Protocol Actions Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + actionId (optional) - Action Id. Maximum length is 22.
    + protocolActionTypeCode (optional) - Protocol Action Type Code. Maximum length is 22.
    + submissionNumber (optional) - Submission Number. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 2000.
    + createTimestamp (optional) - Create Timestamp. Maximum length is 10.
    + createUser (optional) - Create User. Maximum length is 60.
    + actionDate (optional) - Action Date. Maximum length is 10.
    + protocolId (optional) - 
    + submissionIdFk (optional) - 
    + actualActionDate (optional) - 
    + prevSubmissionStatusCode (optional) - 
    + submissionTypeCode (optional) - 
    + prevProtocolStatusCode (optional) - 
    + followupActionCode (optional) - 
    + createdSubmission (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
