## Agenda Items [/research-sys/api/v1/agenda-items/]

### Get Agenda Items by Key [GET /research-sys/api/v1/agenda-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}

### Get All Agenda Items [GET /research-sys/api/v1/agenda-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Agenda Items with Filtering [GET /research-sys/api/v1/agenda-items/]
    
+ Parameters

    + id (optional) - Id.
    + agendaId (optional) - Agenda Id.
    + subAgendaId (optional) - Sub-Agenda Id.
    + whenTrueId (optional) - whenTrue Id.
    + whenFalseId (optional) - whenFalse Id.
    + alwaysId (optional) - Always Id.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Agenda Items [GET /research-sys/api/v1/agenda-items/]
	                                          
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
    
            {"columns":["id","agendaId","subAgendaId","whenTrueId","whenFalseId","alwaysId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Agenda Items [GET /research-sys/api/v1/agenda-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Agenda Items.md"
            transfer-encoding:chunked
### Update Agenda Items [PUT /research-sys/api/v1/agenda-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Agenda Items [PUT /research-sys/api/v1/agenda-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Agenda Items [POST /research-sys/api/v1/agenda-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Agenda Items [POST /research-sys/api/v1/agenda-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","agendaId": "(val)","subAgendaId": "(val)","whenTrueId": "(val)","whenFalseId": "(val)","alwaysId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Agenda Items by Key [DELETE /research-sys/api/v1/agenda-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Agenda Items [DELETE /research-sys/api/v1/agenda-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Agenda Items with Matching [DELETE /research-sys/api/v1/agenda-items/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id.
    + agendaId (optional) - Agenda Id.
    + subAgendaId (optional) - Sub-Agenda Id.
    + whenTrueId (optional) - whenTrue Id.
    + whenFalseId (optional) - whenFalse Id.
    + alwaysId (optional) - Always Id.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
