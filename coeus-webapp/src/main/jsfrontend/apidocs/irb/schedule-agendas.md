## Schedule Agendas [/research-sys/api/v1/schedule-agendas/]

### Get Schedule Agendas by Key [GET /research-sys/api/v1/schedule-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Schedule Agendas [GET /research-sys/api/v1/schedule-agendas/]
	 
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

### Get All Schedule Agendas with Filtering [GET /research-sys/api/v1/schedule-agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + scheduleAgendaId
            + scheduleIdFk
            + agendaNumber
            + agendaName
            + pdfStore
            + createTimestamp
            + createUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Schedule Agendas [GET /research-sys/api/v1/schedule-agendas/]
	 
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
		
### Get Blueprint API specification for Schedule Agendas [GET /research-sys/api/v1/schedule-agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Schedule Agendas.md"
            transfer-encoding:chunked


### Update Schedule Agendas [PUT /research-sys/api/v1/schedule-agendas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Schedule Agendas [PUT /research-sys/api/v1/schedule-agendas/]

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

### Insert Schedule Agendas [POST /research-sys/api/v1/schedule-agendas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleAgendaId": "(val)","scheduleIdFk": "(val)","agendaNumber": "(val)","agendaName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Schedule Agendas [POST /research-sys/api/v1/schedule-agendas/]

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
            
### Delete Schedule Agendas by Key [DELETE /research-sys/api/v1/schedule-agendas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Schedule Agendas [DELETE /research-sys/api/v1/schedule-agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Schedule Agendas with Matching [DELETE /research-sys/api/v1/schedule-agendas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + scheduleAgendaId
            + scheduleIdFk
            + agendaNumber
            + agendaName
            + pdfStore
            + createTimestamp
            + createUser


+ Response 204
