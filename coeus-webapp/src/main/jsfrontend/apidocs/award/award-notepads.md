## Award Notepads [/research-sys/api/v1/award-notepads/]

### Get Award Notepads by Key [GET /research-sys/api/v1/award-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Award Notepads [GET /research-sys/api/v1/award-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Notepads with Filtering [GET /research-sys/api/v1/award-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardNotepadId
            + awardNumber
            + awardId
            + entryNumber
            + noteTopic
            + comments
            + restrictedView
            + createTimestamp
            + createUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Notepads [GET /research-sys/api/v1/award-notepads/]
	 
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
		
### Get Blueprint API specification for Award Notepads [GET /research-sys/api/v1/award-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Notepads.md"
            transfer-encoding:chunked


### Update Award Notepads [PUT /research-sys/api/v1/award-notepads/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Notepads [PUT /research-sys/api/v1/award-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Notepads [POST /research-sys/api/v1/award-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Notepads [POST /research-sys/api/v1/award-notepads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","awardId": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Notepads by Key [DELETE /research-sys/api/v1/award-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Notepads [DELETE /research-sys/api/v1/award-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Notepads with Matching [DELETE /research-sys/api/v1/award-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardNotepadId
            + awardNumber
            + awardId
            + entryNumber
            + noteTopic
            + comments
            + restrictedView
            + createTimestamp
            + createUser


+ Response 204
