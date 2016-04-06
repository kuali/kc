## Protocol Correspondences [/research-sys/api/v1/protocol-correspondences/]

### Get Protocol Correspondences by Key [GET /research-sys/api/v1/protocol-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}

### Get All Protocol Correspondences [GET /research-sys/api/v1/protocol-correspondences/]
	 
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

### Get All Protocol Correspondences with Filtering [GET /research-sys/api/v1/protocol-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + protocolId
            + protocolNumber
            + sequenceNumber
            + actionId
            + actionIdFk
            + protoCorrespTypeCode
            + correspondence
            + finalFlag
            + createUser
            + createTimestamp
            + finalFlagTimestamp
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Correspondences [GET /research-sys/api/v1/protocol-correspondences/]
	 
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
		
### Get Blueprint API specification for Protocol Correspondences [GET /research-sys/api/v1/protocol-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Correspondences.md"
            transfer-encoding:chunked


### Update Protocol Correspondences [PUT /research-sys/api/v1/protocol-correspondences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Correspondences [PUT /research-sys/api/v1/protocol-correspondences/]

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

### Insert Protocol Correspondences [POST /research-sys/api/v1/protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Correspondences [POST /research-sys/api/v1/protocol-correspondences/]

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
            
### Delete Protocol Correspondences by Key [DELETE /research-sys/api/v1/protocol-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondences [DELETE /research-sys/api/v1/protocol-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Correspondences with Matching [DELETE /research-sys/api/v1/protocol-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + protocolId
            + protocolNumber
            + sequenceNumber
            + actionId
            + actionIdFk
            + protoCorrespTypeCode
            + correspondence
            + finalFlag
            + createUser
            + createTimestamp
            + finalFlagTimestamp


+ Response 204
