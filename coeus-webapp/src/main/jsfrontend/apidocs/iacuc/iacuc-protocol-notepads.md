## Iacuc Protocol Notepads [/research-sys/api/v1/iacuc-protocol-notepads/]

### Get Iacuc Protocol Notepads by Key [GET /research-sys/api/v1/iacuc-protocol-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Notepads [GET /research-sys/api/v1/iacuc-protocol-notepads/]
	 
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

### Get All Iacuc Protocol Notepads with Filtering [GET /research-sys/api/v1/iacuc-protocol-notepads/]
    
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
			
### Get Schema for Iacuc Protocol Notepads [GET /research-sys/api/v1/iacuc-protocol-notepads/]
	                                          
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
    
            {"columns":["id","protocolId","protocolNumber","sequenceNumber","entryNumber","comments","restrictedView","noteTopic","createTimestamp","createUser"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Protocol Notepads [GET /research-sys/api/v1/iacuc-protocol-notepads/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Notepads.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Notepads [PUT /research-sys/api/v1/iacuc-protocol-notepads/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Notepads [PUT /research-sys/api/v1/iacuc-protocol-notepads/]

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

### Insert Iacuc Protocol Notepads [POST /research-sys/api/v1/iacuc-protocol-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Notepads [POST /research-sys/api/v1/iacuc-protocol-notepads/]

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
            
### Delete Iacuc Protocol Notepads by Key [DELETE /research-sys/api/v1/iacuc-protocol-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Notepads [DELETE /research-sys/api/v1/iacuc-protocol-notepads/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Notepads with Matching [DELETE /research-sys/api/v1/iacuc-protocol-notepads/]

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

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
