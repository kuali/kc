## Protocol Actions [/irb/api/v1/protocol-actions/]

### Get Protocol Actions by Key [GET /irb/api/v1/protocol-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}

### Get All Protocol Actions [GET /irb/api/v1/protocol-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Actions with Filtering [GET /irb/api/v1/protocol-actions/]
    
+ Parameters

    + protocolActionId (optional) - 
    + actionId (optional) - Action Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + submissionNumber (optional) - 
    + submissionIdFk (optional) - 
    + protocolActionTypeCode (optional) - 
    + comments (optional) - Comments. Maximum length is 2000.
    + prevSubmissionStatusCode (optional) - 
    + submissionTypeCode (optional) - 
    + prevProtocolStatusCode (optional) - 
    + createTimestamp (optional) - Create Timestamp. Maximum length is 10.
    + createUser (optional) - Create User. Maximum length is 60.
    + actionDate (optional) - Action Date. Maximum length is 10.
    + actualActionDate (optional) - 
    + followupActionCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Actions [GET /irb/api/v1/protocol-actions/]
	                                          
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
    
            {"columns":["protocolActionId","actionId","protocolId","protocolNumber","sequenceNumber","submissionNumber","submissionIdFk","protocolActionTypeCode","comments","prevSubmissionStatusCode","submissionTypeCode","prevProtocolStatusCode","createTimestamp","createUser","actionDate","actualActionDate","followupActionCode"],"primaryKey":"protocolActionId"}
		
### Get Blueprint API specification for Protocol Actions [GET /irb/api/v1/protocol-actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Actions.md"
            transfer-encoding:chunked
### Update Protocol Actions [PUT /irb/api/v1/protocol-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Actions [PUT /irb/api/v1/protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Actions [POST /irb/api/v1/protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Actions [POST /irb/api/v1/protocol-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"},
              {"protocolActionId": "(val)","actionId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","submissionIdFk": "(val)","protocolActionTypeCode": "(val)","comments": "(val)","prevSubmissionStatusCode": "(val)","submissionTypeCode": "(val)","prevProtocolStatusCode": "(val)","createTimestamp": "(val)","createUser": "(val)","actionDate": "(val)","actualActionDate": "(val)","followupActionCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Actions by Key [DELETE /irb/api/v1/protocol-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Actions [DELETE /irb/api/v1/protocol-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Actions with Matching [DELETE /irb/api/v1/protocol-actions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolActionId (optional) - 
    + actionId (optional) - Action Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + submissionNumber (optional) - 
    + submissionIdFk (optional) - 
    + protocolActionTypeCode (optional) - 
    + comments (optional) - Comments. Maximum length is 2000.
    + prevSubmissionStatusCode (optional) - 
    + submissionTypeCode (optional) - 
    + prevProtocolStatusCode (optional) - 
    + createTimestamp (optional) - Create Timestamp. Maximum length is 10.
    + createUser (optional) - Create User. Maximum length is 60.
    + actionDate (optional) - Action Date. Maximum length is 10.
    + actualActionDate (optional) - 
    + followupActionCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
