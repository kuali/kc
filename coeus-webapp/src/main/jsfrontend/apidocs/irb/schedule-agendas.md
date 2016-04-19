## Schedule Agendas [/irb/api/v1/schedule-agendas/]

### Get Schedule Agendas by Key [GET /irb/api/v1/schedule-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Schedule Agendas [GET /irb/api/v1/schedule-agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Schedule Agendas with Filtering [GET /irb/api/v1/schedule-agendas/]
    
+ Parameters

    + scheduleAgendaId (optional) - Schedule Agenda Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 12.
    + agendaNumber (optional) - Agenda Number. Maximum length is 22.
    + agendaName (optional) - Agenda Name. Maximum length is 200.
    + pdfStore (optional) - Pdf Store. Maximum length is 4000.
    + createTimestamp (optional) - 
    + createUser (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Schedule Agendas [GET /irb/api/v1/schedule-agendas/]
	                                          
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
    
            {"columns":["scheduleAgendaId","scheduleIdFk","agendaNumber","agendaName","pdfStore","createTimestamp","createUser"],"primaryKey":"scheduleAgendaId"}
		
### Get Blueprint API specification for Schedule Agendas [GET /irb/api/v1/schedule-agendas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Schedule Agendas.md"
            transfer-encoding:chunked


### Update Schedule Agendas [PUT /irb/api/v1/schedule-agendas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Schedule Agendas [PUT /irb/api/v1/schedule-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Schedule Agendas [POST /irb/api/v1/schedule-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Schedule Agendas [POST /irb/api/v1/schedule-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Schedule Agendas by Key [DELETE /irb/api/v1/schedule-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Schedule Agendas [DELETE /irb/api/v1/schedule-agendas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Schedule Agendas with Matching [DELETE /irb/api/v1/schedule-agendas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + scheduleAgendaId (optional) - Schedule Agenda Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 12.
    + agendaNumber (optional) - Agenda Number. Maximum length is 22.
    + agendaName (optional) - Agenda Name. Maximum length is 200.
    + pdfStore (optional) - Pdf Store. Maximum length is 4000.
    + createTimestamp (optional) - 
    + createUser (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
