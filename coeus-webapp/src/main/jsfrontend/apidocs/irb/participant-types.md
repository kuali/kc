## Participant Types [/irb/api/v1/participant-types/]

### Get Participant Types by Key [GET /irb/api/v1/participant-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Participant Types [GET /irb/api/v1/participant-types/]
	 
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

### Get All Participant Types with Filtering [GET /irb/api/v1/participant-types/]
    
+ Parameters

    + participantTypeCode (optional) - Participant Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Participant Types [GET /irb/api/v1/participant-types/]
	                                          
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
    
            {"columns":["participantTypeCode","description"],"primaryKey":"participantTypeCode"}
		
### Get Blueprint API specification for Participant Types [GET /irb/api/v1/participant-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Participant Types.md"
            transfer-encoding:chunked
### Update Participant Types [PUT /irb/api/v1/participant-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Participant Types [PUT /irb/api/v1/participant-types/]

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
### Insert Participant Types [POST /irb/api/v1/participant-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"participantTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Participant Types [POST /irb/api/v1/participant-types/]

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
### Delete Participant Types by Key [DELETE /irb/api/v1/participant-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Participant Types [DELETE /irb/api/v1/participant-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Participant Types with Matching [DELETE /irb/api/v1/participant-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + participantTypeCode (optional) - Participant Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
