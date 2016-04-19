## Committee Schedule Minute Docs [/irb/api/v1/committee-schedule-minute-docs/]

### Get Committee Schedule Minute Docs by Key [GET /irb/api/v1/committee-schedule-minute-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedule Minute Docs [GET /irb/api/v1/committee-schedule-minute-docs/]
	 
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

### Get All Committee Schedule Minute Docs with Filtering [GET /irb/api/v1/committee-schedule-minute-docs/]
    
+ Parameters

    + commScheduleMinuteDocId (optional) - Comm Schedule Minute Doc Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 12.
    + minuteNumber (optional) - Minute Number. Maximum length is 4.
    + minuteName (optional) - Minute Name. Maximum length is 200.
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
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Schedule Minute Docs [GET /irb/api/v1/committee-schedule-minute-docs/]
	                                          
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
    
            {"columns":["commScheduleMinuteDocId","scheduleIdFk","minuteNumber","minuteName","pdfStore","createTimestamp","createUser"],"primaryKey":"commScheduleMinuteDocId"}
		
### Get Blueprint API specification for Committee Schedule Minute Docs [GET /irb/api/v1/committee-schedule-minute-docs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Schedule Minute Docs.md"
            transfer-encoding:chunked


### Update Committee Schedule Minute Docs [PUT /irb/api/v1/committee-schedule-minute-docs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedule Minute Docs [PUT /irb/api/v1/committee-schedule-minute-docs/]

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

### Insert Committee Schedule Minute Docs [POST /irb/api/v1/committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedule Minute Docs [POST /irb/api/v1/committee-schedule-minute-docs/]

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
            
### Delete Committee Schedule Minute Docs by Key [DELETE /irb/api/v1/committee-schedule-minute-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Minute Docs [DELETE /irb/api/v1/committee-schedule-minute-docs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Minute Docs with Matching [DELETE /irb/api/v1/committee-schedule-minute-docs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commScheduleMinuteDocId (optional) - Comm Schedule Minute Doc Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 12.
    + minuteNumber (optional) - Minute Number. Maximum length is 4.
    + minuteName (optional) - Minute Name. Maximum length is 200.
    + pdfStore (optional) - Pdf Store. Maximum length is 4000.
    + createTimestamp (optional) - 
    + createUser (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
