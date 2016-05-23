## Committee Schedules [/irb/api/v1/committee-schedules/]

### Get Committee Schedules by Key [GET /irb/api/v1/committee-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedules [GET /irb/api/v1/committee-schedules/]
	 
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

### Get All Committee Schedules with Filtering [GET /irb/api/v1/committee-schedules/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 12.
    + scheduleId (optional) - Schedule Id. Maximum length is 10.
    + committeeIdFk (optional) - Committee Id. Maximum length is 12.
    + scheduledDate (optional) - Scheduled Date. Maximum length is 10.
    + place (optional) - Place. Maximum length is 200.
    + time (optional) - Time. Maximum length is 10.
    + protocolSubDeadline (optional) - Protocol Sub Deadline. Maximum length is 10.
    + scheduleStatusCode (optional) - Schedule Status Code. Maximum length is 3.
    + meetingDate (optional) - Meeting Date. Maximum length is 21.
    + startTime (optional) - Start Time. Maximum length is 10.
    + endTime (optional) - End Time. Maximum length is 10.
    + agendaProdRevDate (optional) - Agenda Prod Rev Date. Maximum length is 21.
    + maxProtocols (optional) - Max Protocols. Maximum length is 4.
    + comments (optional) - Comments. Maximum length is 2000.
    + availableToReviewers (optional) - Available to reviewers. Maximum length is 1.

            
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
			
### Get Schema for Committee Schedules [GET /irb/api/v1/committee-schedules/]
	                                          
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
		
### Get Blueprint API specification for Committee Schedules [GET /irb/api/v1/committee-schedules/]
	 
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
### Update Committee Schedules [PUT /irb/api/v1/committee-schedules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedules [PUT /irb/api/v1/committee-schedules/]

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
### Insert Committee Schedules [POST /irb/api/v1/committee-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","scheduleId": "(val)","committeeIdFk": "(val)","scheduledDate": "(val)","place": "(val)","time": "(val)","protocolSubDeadline": "(val)","scheduleStatusCode": "(val)","meetingDate": "(val)","startTime": "(val)","endTime": "(val)","agendaProdRevDate": "(val)","maxProtocols": "(val)","comments": "(val)","availableToReviewers": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedules [POST /irb/api/v1/committee-schedules/]

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
### Delete Committee Schedules by Key [DELETE /irb/api/v1/committee-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedules [DELETE /irb/api/v1/committee-schedules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedules with Matching [DELETE /irb/api/v1/committee-schedules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 12.
    + scheduleId (optional) - Schedule Id. Maximum length is 10.
    + committeeIdFk (optional) - Committee Id. Maximum length is 12.
    + scheduledDate (optional) - Scheduled Date. Maximum length is 10.
    + place (optional) - Place. Maximum length is 200.
    + time (optional) - Time. Maximum length is 10.
    + protocolSubDeadline (optional) - Protocol Sub Deadline. Maximum length is 10.
    + scheduleStatusCode (optional) - Schedule Status Code. Maximum length is 3.
    + meetingDate (optional) - Meeting Date. Maximum length is 21.
    + startTime (optional) - Start Time. Maximum length is 10.
    + endTime (optional) - End Time. Maximum length is 10.
    + agendaProdRevDate (optional) - Agenda Prod Rev Date. Maximum length is 21.
    + maxProtocols (optional) - Max Protocols. Maximum length is 4.
    + comments (optional) - Comments. Maximum length is 2000.
    + availableToReviewers (optional) - Available to reviewers. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
