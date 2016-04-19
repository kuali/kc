## Notes [/research-sys/api/v1/notes/]

### Get Notes by Key [GET /research-sys/api/v1/notes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}

### Get All Notes [GET /research-sys/api/v1/notes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notes with Filtering [GET /research-sys/api/v1/notes/]
    
+ Parameters

    + noteIdentifier (optional) - Note Identifier. Maximum length is 14.
    + remoteObjectIdentifier (optional) - remoteObjectIdentifier description... Maximum length is 36.
    + authorUniversalIdentifier (optional) - A free-form text field for the full name of the Author of the Note, expressed as "Lastname, Firstname Initial". Maximum length is 40.
    + notePostedTimestamp (optional) - A free-form text field that identifies the time and date at which the Notes is posted. Maximum length is 36.
    + noteTypeCode (optional) - noteTypeCode description... Maximum length is 4.
    + noteText (optional) - A free-form text field for the text of the Note. Maximum length is 800.
    + noteTopicText (optional) - A free-form text field for entering the topic of the Note. Maximum length is 40.
    + notePurgeCode (optional) - notePurgeCode description... Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notes [GET /research-sys/api/v1/notes/]
	                                          
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
    
            {"columns":["noteIdentifier","remoteObjectIdentifier","authorUniversalIdentifier","notePostedTimestamp","noteTypeCode","noteText","noteTopicText","notePurgeCode"],"primaryKey":"noteIdentifier"}
		
### Get Blueprint API specification for Notes [GET /research-sys/api/v1/notes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notes.md"
            transfer-encoding:chunked


### Update Notes [PUT /research-sys/api/v1/notes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notes [PUT /research-sys/api/v1/notes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Notes [POST /research-sys/api/v1/notes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notes [POST /research-sys/api/v1/notes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"},
              {"noteIdentifier": "(val)","remoteObjectIdentifier": "(val)","authorUniversalIdentifier": "(val)","notePostedTimestamp": "(val)","noteTypeCode": "(val)","noteText": "(val)","noteTopicText": "(val)","notePurgeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Notes by Key [DELETE /research-sys/api/v1/notes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notes [DELETE /research-sys/api/v1/notes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notes with Matching [DELETE /research-sys/api/v1/notes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + noteIdentifier (optional) - Note Identifier. Maximum length is 14.
    + remoteObjectIdentifier (optional) - remoteObjectIdentifier description... Maximum length is 36.
    + authorUniversalIdentifier (optional) - A free-form text field for the full name of the Author of the Note, expressed as "Lastname, Firstname Initial". Maximum length is 40.
    + notePostedTimestamp (optional) - A free-form text field that identifies the time and date at which the Notes is posted. Maximum length is 36.
    + noteTypeCode (optional) - noteTypeCode description... Maximum length is 4.
    + noteText (optional) - A free-form text field for the text of the Note. Maximum length is 800.
    + noteTopicText (optional) - A free-form text field for entering the topic of the Note. Maximum length is 40.
    + notePurgeCode (optional) - notePurgeCode description... Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
