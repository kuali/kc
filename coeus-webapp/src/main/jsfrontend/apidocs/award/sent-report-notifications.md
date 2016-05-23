## Sent Report Notifications [/award/api/v1/sent-report-notifications/]

### Get Sent Report Notifications by Key [GET /award/api/v1/sent-report-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}

### Get All Sent Report Notifications [GET /award/api/v1/sent-report-notifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"},
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sent Report Notifications with Filtering [GET /award/api/v1/sent-report-notifications/]
    
+ Parameters

    + awardReportNotifSentId (optional) - 
    + awardReportTermId (optional) - 
    + awardNumber (optional) - 
    + dueDate (optional) - 
    + actionCode (optional) - 
    + dateSent (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"},
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sent Report Notifications [GET /award/api/v1/sent-report-notifications/]
	                                          
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
    
            {"columns":["awardReportNotifSentId","awardReportTermId","awardNumber","dueDate","actionCode","dateSent"],"primaryKey":"awardReportNotifSentId"}
		
### Get Blueprint API specification for Sent Report Notifications [GET /award/api/v1/sent-report-notifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sent Report Notifications.md"
            transfer-encoding:chunked
### Update Sent Report Notifications [PUT /award/api/v1/sent-report-notifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sent Report Notifications [PUT /award/api/v1/sent-report-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"},
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sent Report Notifications [POST /award/api/v1/sent-report-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sent Report Notifications [POST /award/api/v1/sent-report-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"},
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"},
              {"awardReportNotifSentId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","dueDate": "(val)","actionCode": "(val)","dateSent": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sent Report Notifications by Key [DELETE /award/api/v1/sent-report-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sent Report Notifications [DELETE /award/api/v1/sent-report-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sent Report Notifications with Matching [DELETE /award/api/v1/sent-report-notifications/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardReportNotifSentId (optional) - 
    + awardReportTermId (optional) - 
    + awardNumber (optional) - 
    + dueDate (optional) - 
    + actionCode (optional) - 
    + dateSent (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
