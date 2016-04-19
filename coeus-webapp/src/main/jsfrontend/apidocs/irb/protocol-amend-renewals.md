## Protocol Amend Renewals [/irb/api/v1/protocol-amend-renewals/]

### Get Protocol Amend Renewals by Key [GET /irb/api/v1/protocol-amend-renewals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Protocol Amend Renewals [GET /irb/api/v1/protocol-amend-renewals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Amend Renewals with Filtering [GET /irb/api/v1/protocol-amend-renewals/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 10.
    + protoAmendRenNumber (optional) - Proto Amend Ren Number. Maximum length is 20.
    + dateCreated (optional) - Date Created. Maximum length is 10.
    + summary (optional) - Summary. Maximum length is 4000.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Amend Renewals [GET /irb/api/v1/protocol-amend-renewals/]
	                                          
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
    
            {"columns":["id","protoAmendRenNumber","dateCreated","summary","protocolId","protocolNumber","sequenceNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Protocol Amend Renewals [GET /irb/api/v1/protocol-amend-renewals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Amend Renewals.md"
            transfer-encoding:chunked


### Update Protocol Amend Renewals [PUT /irb/api/v1/protocol-amend-renewals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Amend Renewals [PUT /irb/api/v1/protocol-amend-renewals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Amend Renewals [POST /irb/api/v1/protocol-amend-renewals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Amend Renewals [POST /irb/api/v1/protocol-amend-renewals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Amend Renewals by Key [DELETE /irb/api/v1/protocol-amend-renewals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Amend Renewals [DELETE /irb/api/v1/protocol-amend-renewals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Amend Renewals with Matching [DELETE /irb/api/v1/protocol-amend-renewals/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 10.
    + protoAmendRenNumber (optional) - Proto Amend Ren Number. Maximum length is 20.
    + dateCreated (optional) - Date Created. Maximum length is 10.
    + summary (optional) - Summary. Maximum length is 4000.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
