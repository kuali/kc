## Award Report Terms [/research-sys/api/v1/award-report-terms/]

### Get Award Report Terms by Key [GET /research-sys/api/v1/award-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}

### Get All Award Report Terms [GET /research-sys/api/v1/award-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Report Terms with Filtering [GET /research-sys/api/v1/award-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardReportTermId
            + awardId
            + awardNumber
            + sequenceNumber
            + reportClassCode
            + reportCode
            + frequencyCode
            + frequencyBaseCode
            + ospDistributionCode
            + dueDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Report Terms [GET /research-sys/api/v1/award-report-terms/]
	 
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
		
### Get Blueprint API specification for Award Report Terms [GET /research-sys/api/v1/award-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Report Terms.md"
            transfer-encoding:chunked


### Update Award Report Terms [PUT /research-sys/api/v1/award-report-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Report Terms [PUT /research-sys/api/v1/award-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Report Terms [POST /research-sys/api/v1/award-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Report Terms [POST /research-sys/api/v1/award-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Report Terms by Key [DELETE /research-sys/api/v1/award-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Report Terms [DELETE /research-sys/api/v1/award-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Report Terms with Matching [DELETE /research-sys/api/v1/award-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardReportTermId
            + awardId
            + awardNumber
            + sequenceNumber
            + reportClassCode
            + reportCode
            + frequencyCode
            + frequencyBaseCode
            + ospDistributionCode
            + dueDate


+ Response 204
