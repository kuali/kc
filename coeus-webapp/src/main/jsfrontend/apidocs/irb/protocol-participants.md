## Protocol Participants [/irb/api/v1/protocol-participants/]

### Get Protocol Participants by Key [GET /irb/api/v1/protocol-participants/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}

### Get All Protocol Participants [GET /irb/api/v1/protocol-participants/]
	 
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

### Get All Protocol Participants with Filtering [GET /irb/api/v1/protocol-participants/]
    
+ Parameters

    + protocolParticipantId (optional) - Protocol Participant Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + participantTypeCode (optional) - Participant Type Code. Maximum length is 3.
    + participantCount (optional) - Participant Count. Maximum length is 6.

            
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
			
### Get Schema for Protocol Participants [GET /irb/api/v1/protocol-participants/]
	                                          
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
    
            {"columns":["protocolParticipantId","protocolId","protocolNumber","sequenceNumber","participantTypeCode","participantCount"],"primaryKey":"protocolParticipantId"}
		
### Get Blueprint API specification for Protocol Participants [GET /irb/api/v1/protocol-participants/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Participants.md"
            transfer-encoding:chunked
### Update Protocol Participants [PUT /irb/api/v1/protocol-participants/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Participants [PUT /irb/api/v1/protocol-participants/]

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
### Insert Protocol Participants [POST /irb/api/v1/protocol-participants/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolParticipantId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","participantTypeCode": "(val)","participantCount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Participants [POST /irb/api/v1/protocol-participants/]

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
### Delete Protocol Participants by Key [DELETE /irb/api/v1/protocol-participants/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Participants [DELETE /irb/api/v1/protocol-participants/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Participants with Matching [DELETE /irb/api/v1/protocol-participants/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolParticipantId (optional) - Protocol Participant Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + participantTypeCode (optional) - Participant Type Code. Maximum length is 3.
    + participantCount (optional) - Participant Count. Maximum length is 6.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
