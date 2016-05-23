## Committee Schedule Attendances [/irb/api/v1/committee-schedule-attendances/]

### Get Committee Schedule Attendances by Key [GET /irb/api/v1/committee-schedule-attendances/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedule Attendances [GET /irb/api/v1/committee-schedule-attendances/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Schedule Attendances with Filtering [GET /irb/api/v1/committee-schedule-attendances/]
    
+ Parameters

    + commScheduleAttendanceId (optional) - Comm Schedule Attendance Id. Maximum length is 22.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 10.
    + personId (optional) - Person Id. Maximum length is 40.
    + personName (optional) - Person Name. Maximum length is 90.
    + guestFlag (optional) - Guest Flag. Maximum length is 1.
    + alternateFlag (optional) - Alternate Flag. Maximum length is 1.
    + alternateFor (optional) - Alternate For. Maximum length is 9.
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Schedule Attendances [GET /irb/api/v1/committee-schedule-attendances/]
	                                          
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
    
            {"columns":["commScheduleAttendanceId","scheduleIdFk","personId","personName","guestFlag","alternateFlag","alternateFor","nonEmployeeFlag","comments"],"primaryKey":"commScheduleAttendanceId"}
		
### Get Blueprint API specification for Committee Schedule Attendances [GET /irb/api/v1/committee-schedule-attendances/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Schedule Attendances.md"
            transfer-encoding:chunked
### Update Committee Schedule Attendances [PUT /irb/api/v1/committee-schedule-attendances/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedule Attendances [PUT /irb/api/v1/committee-schedule-attendances/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Committee Schedule Attendances [POST /irb/api/v1/committee-schedule-attendances/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedule Attendances [POST /irb/api/v1/committee-schedule-attendances/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"commScheduleAttendanceId": "(val)","scheduleIdFk": "(val)","personId": "(val)","personName": "(val)","guestFlag": "(val)","alternateFlag": "(val)","alternateFor": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
### Delete Committee Schedule Attendances by Key [DELETE /irb/api/v1/committee-schedule-attendances/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Attendances [DELETE /irb/api/v1/committee-schedule-attendances/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Attendances with Matching [DELETE /irb/api/v1/committee-schedule-attendances/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commScheduleAttendanceId (optional) - Comm Schedule Attendance Id. Maximum length is 22.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 10.
    + personId (optional) - Person Id. Maximum length is 40.
    + personName (optional) - Person Name. Maximum length is 90.
    + guestFlag (optional) - Guest Flag. Maximum length is 1.
    + alternateFlag (optional) - Alternate Flag. Maximum length is 1.
    + alternateFor (optional) - Alternate For. Maximum length is 9.
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
