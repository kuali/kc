## Institutional Proposal Notepads [/instprop/api/v1/institutional-proposal-notepads/]

### Get Institutional Proposal Notepads by Key [GET /instprop/api/v1/institutional-proposal-notepads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNotepadId": "(val)","proposalNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","noteId": "(val)","createTimestamp": "(val)","createUser": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Notepads [GET /instprop/api/v1/institutional-proposal-notepads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNotepadId": "(val)","proposalNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","noteId": "(val)","createTimestamp": "(val)","createUser": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"proposalNotepadId": "(val)","proposalNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","noteId": "(val)","createTimestamp": "(val)","createUser": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Notepads with Filtering [GET /instprop/api/v1/institutional-proposal-notepads/]
    
+ Parameters

    + proposalNotepadId (optional) - Proposal Notepad Id. Maximum length is 22.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + entryNumber (optional) - Entry Number. Maximum length is 22.
    + noteTopic (optional) - Comments. Maximum length is 60.
    + comments (optional) - Comments. Maximum length is 4000.
    + restrictedView (optional) - Restricted View. Maximum length is 1.
    + noteId (optional) - 
    + createTimestamp (optional) - Posted Timestamp. Maximum length is 10.
    + createUser (optional) - 
    + institutionalProposal.proposalId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNotepadId": "(val)","proposalNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","noteId": "(val)","createTimestamp": "(val)","createUser": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"proposalNotepadId": "(val)","proposalNumber": "(val)","entryNumber": "(val)","noteTopic": "(val)","comments": "(val)","restrictedView": "(val)","noteId": "(val)","createTimestamp": "(val)","createUser": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Notepads [GET /instprop/api/v1/institutional-proposal-notepads/]
	                                          
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
    
            {"columns":["proposalNotepadId","proposalNumber","entryNumber","noteTopic","comments","restrictedView","noteId","createTimestamp","createUser","institutionalProposal.proposalId"],"primaryKey":"proposalNotepadId"}
		
### Get Blueprint API specification for Institutional Proposal Notepads [GET /instprop/api/v1/institutional-proposal-notepads/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Notepads.md"
            transfer-encoding:chunked
