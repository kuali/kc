## Protocol Participants [/research-sys/api/v1/protocol-participants/]

### Get Protocol Participants by Key [GET /research-sys/api/v1/protocol-participants/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}

### Get All Protocol Participants [GET /research-sys/api/v1/protocol-participants/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"},
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Participants with Filtering [GET /research-sys/api/v1/protocol-participants/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolParticipantId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + participantTypeCode
            + participantCount
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"},
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Participants [GET /research-sys/api/v1/protocol-participants/]
	 
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
		
### Get Blueprint API specification for Protocol Participants [GET /research-sys/api/v1/protocol-participants/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Participants.md"
            transfer-encoding:chunked


### Update Protocol Participants [PUT /research-sys/api/v1/protocol-participants/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Participants [PUT /research-sys/api/v1/protocol-participants/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"},
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Participants [POST /research-sys/api/v1/protocol-participants/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Participants [POST /research-sys/api/v1/protocol-participants/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"},
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"},
              {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Participants by Key [DELETE /research-sys/api/v1/protocol-participants/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Participants [DELETE /research-sys/api/v1/protocol-participants/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Participants with Matching [DELETE /research-sys/api/v1/protocol-participants/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolParticipantId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + participantTypeCode
            + participantCount


+ Response 204
