## Award Template Report Terms [/research-sys/api/v1/award-template-report-terms/]

### Get Award Template Report Terms by Key [GET /research-sys/api/v1/award-template-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}

### Get All Award Template Report Terms [GET /research-sys/api/v1/award-template-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Report Terms with Filtering [GET /research-sys/api/v1/award-template-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + templateReportTermId
            + templateCode
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
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Report Terms [GET /research-sys/api/v1/award-template-report-terms/]
	 
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
		
### Get Blueprint API specification for Award Template Report Terms [GET /research-sys/api/v1/award-template-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Report Terms.md"
            transfer-encoding:chunked


### Update Award Template Report Terms [PUT /research-sys/api/v1/award-template-report-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Report Terms [PUT /research-sys/api/v1/award-template-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Template Report Terms [POST /research-sys/api/v1/award-template-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Report Terms [POST /research-sys/api/v1/award-template-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","templateCode": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Template Report Terms by Key [DELETE /research-sys/api/v1/award-template-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Report Terms [DELETE /research-sys/api/v1/award-template-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Template Report Terms with Matching [DELETE /research-sys/api/v1/award-template-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + templateReportTermId
            + templateCode
            + reportClassCode
            + reportCode
            + frequencyCode
            + frequencyBaseCode
            + ospDistributionCode
            + dueDate


+ Response 204
