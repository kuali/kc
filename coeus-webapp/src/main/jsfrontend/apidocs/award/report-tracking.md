## Report Tracking [/research-sys/api/v1/report-tracking/]

### Get Report Tracking by Key [GET /research-sys/api/v1/report-tracking/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}

### Get All Report Tracking [GET /research-sys/api/v1/report-tracking/]
	 
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

### Get All Report Tracking with Filtering [GET /research-sys/api/v1/report-tracking/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardReportTrackingId
            + awardReportTermId
            + awardNumber
            + piPersonId
            + piRolodexId
            + piName
            + leadUnitNumber
            + reportClassCode
            + reportCode
            + frequencyCode
            + frequencyBaseCode
            + ospDistributionCode
            + statusCode
            + baseDate
            + dueDate
            + overdue
            + activityDate
            + comments
            + preparerId
            + preparerName
            + sponsorCode
            + sponsorAwardNumber
            + title
            + lastUpdateUser
            + lastUpdateDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Report Tracking [GET /research-sys/api/v1/report-tracking/]
	 
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
		
### Get Blueprint API specification for Report Tracking [GET /research-sys/api/v1/report-tracking/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Report Tracking.md"
            transfer-encoding:chunked


### Update Report Tracking [PUT /research-sys/api/v1/report-tracking/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Report Tracking [PUT /research-sys/api/v1/report-tracking/]

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

### Insert Report Tracking [POST /research-sys/api/v1/report-tracking/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportTrackingId": "(val)","awardReportTermId": "(val)","awardNumber": "(val)","piPersonId": "(val)","piRolodexId": "(val)","piName": "(val)","leadUnitNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","statusCode": "(val)","baseDate": "(val)","dueDate": "(val)","overdue": "(val)","activityDate": "(val)","comments": "(val)","preparerId": "(val)","preparerName": "(val)","sponsorCode": "(val)","sponsorAwardNumber": "(val)","title": "(val)","lastUpdateUser": "(val)","lastUpdateDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Report Tracking [POST /research-sys/api/v1/report-tracking/]

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
            
### Delete Report Tracking by Key [DELETE /research-sys/api/v1/report-tracking/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Tracking [DELETE /research-sys/api/v1/report-tracking/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Report Tracking with Matching [DELETE /research-sys/api/v1/report-tracking/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardReportTrackingId
            + awardReportTermId
            + awardNumber
            + piPersonId
            + piRolodexId
            + piName
            + leadUnitNumber
            + reportClassCode
            + reportCode
            + frequencyCode
            + frequencyBaseCode
            + ospDistributionCode
            + statusCode
            + baseDate
            + dueDate
            + overdue
            + activityDate
            + comments
            + preparerId
            + preparerName
            + sponsorCode
            + sponsorAwardNumber
            + title
            + lastUpdateUser
            + lastUpdateDate


+ Response 204
