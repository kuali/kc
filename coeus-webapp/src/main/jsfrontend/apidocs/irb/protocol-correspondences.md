## Protocol Correspondences [/irb/api/v1/protocol-correspondences/]

### Get Protocol Correspondences by Key [GET /irb/api/v1/protocol-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}

### Get All Protocol Correspondences [GET /irb/api/v1/protocol-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Correspondences with Filtering [GET /irb/api/v1/protocol-correspondences/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + actionId (optional) - 
    + actionIdFk (optional) - 
    + protoCorrespTypeCode (optional) - 
    + correspondence (optional) - Correspondence. Maximum length is 4000.
    + finalFlag (optional) - Is Final. Maximum length is 1.
    + createUser (optional) - Create User. Maximum length is 80.
    + createTimestamp (optional) - Created Time. Maximum length is 21.
    + finalFlagTimestamp (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Correspondences [GET /irb/api/v1/protocol-correspondences/]
	                                          
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
    
            {"columns":["id","protocolId","protocolNumber","sequenceNumber","actionId","actionIdFk","protoCorrespTypeCode","correspondence","finalFlag","createUser","createTimestamp","finalFlagTimestamp"],"primaryKey":"id"}
		
### Get Blueprint API specification for Protocol Correspondences [GET /irb/api/v1/protocol-correspondences/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Correspondences.md"
            transfer-encoding:chunked
### Update Protocol Correspondences [PUT /irb/api/v1/protocol-correspondences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Correspondences [PUT /irb/api/v1/protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Correspondences [POST /irb/api/v1/protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Correspondences [POST /irb/api/v1/protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Correspondences by Key [DELETE /irb/api/v1/protocol-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondences [DELETE /irb/api/v1/protocol-correspondences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondences with Matching [DELETE /irb/api/v1/protocol-correspondences/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + actionId (optional) - 
    + actionIdFk (optional) - 
    + protoCorrespTypeCode (optional) - 
    + correspondence (optional) - Correspondence. Maximum length is 4000.
    + finalFlag (optional) - Is Final. Maximum length is 1.
    + createUser (optional) - Create User. Maximum length is 80.
    + createTimestamp (optional) - Created Time. Maximum length is 21.
    + finalFlagTimestamp (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
