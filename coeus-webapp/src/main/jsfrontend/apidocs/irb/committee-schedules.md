## Committee Schedules [/research-sys/api/v1/committee-schedules/]

### Get Committee Schedules by Key [GET /research-sys/api/v1/committee-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedules [GET /research-sys/api/v1/committee-schedules/]
	 
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

### Get All Committee Schedules with Filtering [GET /research-sys/api/v1/committee-schedules/]
    
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
			
### Get Schema for Committee Schedules [GET /research-sys/api/v1/committee-schedules/]
	                                          
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
		
### Get Blueprint API specification for Committee Schedules [GET /research-sys/api/v1/committee-schedules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Schedules.md"
            transfer-encoding:chunked


### Update Committee Schedules [PUT /research-sys/api/v1/committee-schedules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedules [PUT /research-sys/api/v1/committee-schedules/]

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

### Insert Committee Schedules [POST /research-sys/api/v1/committee-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedules [POST /research-sys/api/v1/committee-schedules/]

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
            
### Delete Committee Schedules by Key [DELETE /research-sys/api/v1/committee-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedules [DELETE /research-sys/api/v1/committee-schedules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedules with Matching [DELETE /research-sys/api/v1/committee-schedules/]

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
