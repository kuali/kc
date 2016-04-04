## Protocol Notepads [/research-sys/api/v1/protocol-notepads/]

### Get Protocol Notepads by Key [GET /research-sys/api/v1/protocol-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Protocol Notepads [GET /research-sys/api/v1/protocol-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Notepads with Filtering [GET /research-sys/api/v1/protocol-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + protocolId
            + protocolNumber
            + sequenceNumber
            + entryNumber
            + comments
            + restrictedView
            + noteTopic
            + createTimestamp
            + createUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Notepads [GET /research-sys/api/v1/protocol-notepads/]
	 
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
		
### Get Blueprint API specification for Protocol Notepads [GET /research-sys/api/v1/protocol-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Notepads.md"
            transfer-encoding:chunked


### Update Protocol Notepads [PUT /research-sys/api/v1/protocol-notepads/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Notepads [PUT /research-sys/api/v1/protocol-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Notepads [POST /research-sys/api/v1/protocol-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Notepads [POST /research-sys/api/v1/protocol-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Notepads by Key [DELETE /research-sys/api/v1/protocol-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notepads [DELETE /research-sys/api/v1/protocol-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Notepads with Matching [DELETE /research-sys/api/v1/protocol-notepads/]
	 
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
            + entryNumber
            + comments
            + restrictedView
            + noteTopic
            + createTimestamp
            + createUser


+ Response 204
