## Iacuc Committee Schedules [/research-sys/api/v1/iacuc-committee-schedules/]

### Get Iacuc Committee Schedules by Key [GET /research-sys/api/v1/iacuc-committee-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedules [GET /research-sys/api/v1/iacuc-committee-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committee Schedules with Filtering [GET /research-sys/api/v1/iacuc-committee-schedules/]
    
+ Parameters

        + id
            + scheduleId
            + committeeIdFk
            + scheduledDate
            + place
            + time
            + protocolSubDeadline
            + scheduleStatusCode
            + meetingDate
            + startTime
            + endTime
            + agendaProdRevDate
            + maxProtocols
            + comments
            + availableToReviewers

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Schedules [GET /research-sys/api/v1/iacuc-committee-schedules/]
	                                          
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
    
            {"columns":["id","scheduleId","committeeIdFk","scheduledDate","place","time","protocolSubDeadline","scheduleStatusCode","meetingDate","startTime","endTime","agendaProdRevDate","maxProtocols","comments","availableToReviewers"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Committee Schedules [GET /research-sys/api/v1/iacuc-committee-schedules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedules.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedules [PUT /research-sys/api/v1/iacuc-committee-schedules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedules [PUT /research-sys/api/v1/iacuc-committee-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Committee Schedules [POST /research-sys/api/v1/iacuc-committee-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedules [POST /research-sys/api/v1/iacuc-committee-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Committee Schedules by Key [DELETE /research-sys/api/v1/iacuc-committee-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedules [DELETE /research-sys/api/v1/iacuc-committee-schedules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedules with Matching [DELETE /research-sys/api/v1/iacuc-committee-schedules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + scheduleId
            + committeeIdFk
            + scheduledDate
            + place
            + time
            + protocolSubDeadline
            + scheduleStatusCode
            + meetingDate
            + startTime
            + endTime
            + agendaProdRevDate
            + maxProtocols
            + comments
            + availableToReviewers

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
