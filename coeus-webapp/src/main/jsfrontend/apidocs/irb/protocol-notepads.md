## Protocol Notepads [/irb/api/v1/protocol-notepads/]

### Get Protocol Notepads by Key [GET /irb/api/v1/protocol-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Protocol Notepads [GET /irb/api/v1/protocol-notepads/]
	 
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

### Get All Protocol Notepads with Filtering [GET /irb/api/v1/protocol-notepads/]
    
+ Parameters

    + id (optional) - Protocol Notepad Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - 
    + entryNumber (optional) - Entry Number. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 4000.
    + restrictedView (optional) - Restricted View. Maximum length is 1.
    + noteTopic (optional) - Comments. Maximum length is 60.
    + createTimestamp (optional) - The creation or last modification timestamp. Maximum length is 21.
    + createUser (optional) - The user who created or last modified the object. Maximum length is 60.

            
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
			
### Get Schema for Protocol Notepads [GET /irb/api/v1/protocol-notepads/]
	                                          
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
		
### Get Blueprint API specification for Protocol Notepads [GET /irb/api/v1/protocol-notepads/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Notepads.md"
            transfer-encoding:chunked
### Update Protocol Notepads [PUT /irb/api/v1/protocol-notepads/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Notepads [PUT /irb/api/v1/protocol-notepads/]

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
### Insert Protocol Notepads [POST /irb/api/v1/protocol-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","entryNumber": "(val)","comments": "(val)","restrictedView": "(val)","noteTopic": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Notepads [POST /irb/api/v1/protocol-notepads/]

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
### Delete Protocol Notepads by Key [DELETE /irb/api/v1/protocol-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notepads [DELETE /irb/api/v1/protocol-notepads/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notepads with Matching [DELETE /irb/api/v1/protocol-notepads/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Protocol Notepad Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - 
    + entryNumber (optional) - Entry Number. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 4000.
    + restrictedView (optional) - Restricted View. Maximum length is 1.
    + noteTopic (optional) - Comments. Maximum length is 60.
    + createTimestamp (optional) - The creation or last modification timestamp. Maximum length is 21.
    + createUser (optional) - The user who created or last modified the object. Maximum length is 60.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
