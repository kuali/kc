## Participant Types [/research-sys/api/v1/participant-types/]

### Get Participant Types by Key [GET /research-sys/api/v1/participant-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Participant Types [GET /research-sys/api/v1/participant-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Participant Types with Filtering [GET /research-sys/api/v1/participant-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + participantTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Participant Types [GET /research-sys/api/v1/participant-types/]
	 
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
		
### Get Blueprint API specification for Participant Types [GET /research-sys/api/v1/participant-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Participant Types.md"
            transfer-encoding:chunked


### Update Participant Types [PUT /research-sys/api/v1/participant-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Participant Types [PUT /research-sys/api/v1/participant-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Participant Types [POST /research-sys/api/v1/participant-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Participant Types [POST /research-sys/api/v1/participant-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Participant Types by Key [DELETE /research-sys/api/v1/participant-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Participant Types [DELETE /research-sys/api/v1/participant-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Participant Types with Matching [DELETE /research-sys/api/v1/participant-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + participantTypeCode
            + description


+ Response 204
