## Report Tracking [/award/api/v1/report-tracking/]

### Get Report Tracking by Key [GET /award/api/v1/report-tracking/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}

### Get All Report Tracking [GET /award/api/v1/report-tracking/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Report Tracking with Filtering [GET /award/api/v1/report-tracking/]
    
+ Parameters

    + awardReportTrackingId (optional) - Award Report Tracking Id. Maximum length is 40.
    + awardReportTermId (optional) - Award Report Terms Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + piPersonId (optional) - 
    + piRolodexId (optional) - 
    + piName (optional) - Full Name. Maximum length is 90.
    + leadUnitNumber (optional) - The lead unit number for the Award. Maximum length is 150.
    + reportClassCode (optional) - Report Class. Maximum length is 22.
    + reportCode (optional) - Report. Maximum length is 22.
    + frequencyCode (optional) - Frequency. Maximum length is 22.
    + frequencyBaseCode (optional) - Frequency Base. Maximum length is 22.
    + ospDistributionCode (optional) - OSP File Copy. Maximum length is 22.
    + statusCode (optional) - Status. Maximum length is 22.
    + baseDate (optional) - Base Date. Maximum length is 10.
    + dueDate (optional) - Due Date. Maximum length is 10.
    + overdue (optional) - Overdue. Maximum length is 22.
    + activityDate (optional) - Activity Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 200.
    + preparerId (optional) - Preparer. Maximum length is 40.
    + preparerName (optional) - Preparer Name. Maximum length is 90.
    + sponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + sponsorAwardNumber (optional) - Sponsor Award ID. Maximum length is 70.
    + title (optional) - The proposed title of the project. Maximum length is 200.
    + lastUpdateUser (optional) - Last Updated By. Maximum length is 90.
    + lastUpdateDate (optional) - Last Updated Date. Maximum length is 10.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Report Tracking [GET /award/api/v1/report-tracking/]
	                                          
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
    
            {"columns":["awardReportTrackingId","awardReportTermId","awardNumber","piPersonId","piRolodexId","piName","leadUnitNumber","reportClassCode","reportCode","frequencyCode","frequencyBaseCode","ospDistributionCode","statusCode","baseDate","dueDate","overdue","activityDate","comments","preparerId","preparerName","sponsorCode","sponsorAwardNumber","title","lastUpdateUser","lastUpdateDate"],"primaryKey":"awardReportTrackingId"}
		
### Get Blueprint API specification for Report Tracking [GET /award/api/v1/report-tracking/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Report Tracking.md"
            transfer-encoding:chunked


### Update Report Tracking [PUT /award/api/v1/report-tracking/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Report Tracking [PUT /award/api/v1/report-tracking/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Report Tracking [POST /award/api/v1/report-tracking/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Report Tracking [POST /award/api/v1/report-tracking/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Report Tracking by Key [DELETE /award/api/v1/report-tracking/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Tracking [DELETE /award/api/v1/report-tracking/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Tracking with Matching [DELETE /award/api/v1/report-tracking/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardReportTrackingId (optional) - Award Report Tracking Id. Maximum length is 40.
    + awardReportTermId (optional) - Award Report Terms Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + piPersonId (optional) - 
    + piRolodexId (optional) - 
    + piName (optional) - Full Name. Maximum length is 90.
    + leadUnitNumber (optional) - The lead unit number for the Award. Maximum length is 150.
    + reportClassCode (optional) - Report Class. Maximum length is 22.
    + reportCode (optional) - Report. Maximum length is 22.
    + frequencyCode (optional) - Frequency. Maximum length is 22.
    + frequencyBaseCode (optional) - Frequency Base. Maximum length is 22.
    + ospDistributionCode (optional) - OSP File Copy. Maximum length is 22.
    + statusCode (optional) - Status. Maximum length is 22.
    + baseDate (optional) - Base Date. Maximum length is 10.
    + dueDate (optional) - Due Date. Maximum length is 10.
    + overdue (optional) - Overdue. Maximum length is 22.
    + activityDate (optional) - Activity Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 200.
    + preparerId (optional) - Preparer. Maximum length is 40.
    + preparerName (optional) - Preparer Name. Maximum length is 90.
    + sponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + sponsorAwardNumber (optional) - Sponsor Award ID. Maximum length is 70.
    + title (optional) - The proposed title of the project. Maximum length is 200.
    + lastUpdateUser (optional) - Last Updated By. Maximum length is 90.
    + lastUpdateDate (optional) - Last Updated Date. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
