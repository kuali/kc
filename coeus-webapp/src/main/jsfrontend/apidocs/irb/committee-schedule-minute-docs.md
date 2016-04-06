## Committee Schedule Minute Docs [/research-sys/api/v1/committee-schedule-minute-docs/]

### Get Committee Schedule Minute Docs by Key [GET /research-sys/api/v1/committee-schedule-minute-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedule Minute Docs [GET /research-sys/api/v1/committee-schedule-minute-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Schedule Minute Docs with Filtering [GET /research-sys/api/v1/committee-schedule-minute-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + commScheduleMinuteDocId
            + scheduleIdFk
            + minuteNumber
            + minuteName
            + pdfStore
            + createTimestamp
            + createUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Schedule Minute Docs [GET /research-sys/api/v1/committee-schedule-minute-docs/]
	 
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
		
### Get Blueprint API specification for Committee Schedule Minute Docs [GET /research-sys/api/v1/committee-schedule-minute-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Schedule Minute Docs.md"
            transfer-encoding:chunked


### Update Committee Schedule Minute Docs [PUT /research-sys/api/v1/committee-schedule-minute-docs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedule Minute Docs [PUT /research-sys/api/v1/committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Schedule Minute Docs [POST /research-sys/api/v1/committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedule Minute Docs [POST /research-sys/api/v1/committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Schedule Minute Docs by Key [DELETE /research-sys/api/v1/committee-schedule-minute-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Minute Docs [DELETE /research-sys/api/v1/committee-schedule-minute-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Committee Schedule Minute Docs with Matching [DELETE /research-sys/api/v1/committee-schedule-minute-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + commScheduleMinuteDocId
            + scheduleIdFk
            + minuteNumber
            + minuteName
            + pdfStore
            + createTimestamp
            + createUser


+ Response 204
