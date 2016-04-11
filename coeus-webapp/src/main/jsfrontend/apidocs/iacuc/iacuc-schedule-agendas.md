## Iacuc Schedule Agendas [/research-sys/api/v1/iacuc-schedule-agendas/]

### Get Iacuc Schedule Agendas by Key [GET /research-sys/api/v1/iacuc-schedule-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Schedule Agendas [GET /research-sys/api/v1/iacuc-schedule-agendas/]
	 
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

### Get All Iacuc Schedule Agendas with Filtering [GET /research-sys/api/v1/iacuc-schedule-agendas/]
    
+ Parameters

        + scheduleAgendaId
            + scheduleIdFk
            + agendaNumber
            + agendaName
            + pdfStore
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
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Schedule Agendas [GET /research-sys/api/v1/iacuc-schedule-agendas/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Schedule Agendas [GET /research-sys/api/v1/iacuc-schedule-agendas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Schedule Agendas.md"
            transfer-encoding:chunked


### Update Iacuc Schedule Agendas [PUT /research-sys/api/v1/iacuc-schedule-agendas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Schedule Agendas [PUT /research-sys/api/v1/iacuc-schedule-agendas/]

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

### Insert Iacuc Schedule Agendas [POST /research-sys/api/v1/iacuc-schedule-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Schedule Agendas [POST /research-sys/api/v1/iacuc-schedule-agendas/]

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
            
### Delete Iacuc Schedule Agendas by Key [DELETE /research-sys/api/v1/iacuc-schedule-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Schedule Agendas [DELETE /research-sys/api/v1/iacuc-schedule-agendas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Schedule Agendas with Matching [DELETE /research-sys/api/v1/iacuc-schedule-agendas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + scheduleAgendaId
            + scheduleIdFk
            + agendaNumber
            + agendaName
            + pdfStore
            + createTimestamp
            + createUser

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
