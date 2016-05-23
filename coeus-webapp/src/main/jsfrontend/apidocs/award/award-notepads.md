## Award Notepads [/award/api/v1/award-notepads/]

### Get Award Notepads by Key [GET /award/api/v1/award-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNotepadId": "(val)","awardNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Notepads [GET /award/api/v1/award-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Notepads with Filtering [GET /award/api/v1/award-notepads/]
    
+ Parameters

    + awardNotepadId (optional) - Award Notepad Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + entryNumber (optional) - Entry Number. Maximum length is 22.
    + noteTopic (optional) - Comments. Maximum length is 60.
    + comments (optional) - Comments. Maximum length is 4000.
    + restrictedView (optional) - Restricted View. Maximum length is 1.
    + createTimestamp (optional) - Posted Timestamp. Maximum length is 10.
    + createUser (optional) - The user who created or last modified the object. Maximum length is 60.
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNotepadId": "(val)","awardNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardNotepadId": "(val)","awardNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","createTimestamp": "(val)","createUser": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Notepads [GET /award/api/v1/award-notepads/]
	                                          
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
    
            {"columns":["awardNotepadId","awardNumber","entryNumber","noteTopic","comments","restrictedView","createTimestamp","createUser","award.awardId"],"primaryKey":"awardNotepadId"}
		
### Get Blueprint API specification for Award Notepads [GET /award/api/v1/award-notepads/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Notepads.md"
            transfer-encoding:chunked
