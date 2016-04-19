## Award Report Terms [/award/api/v1/award-report-terms/]

### Get Award Report Terms by Key [GET /award/api/v1/award-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}

### Get All Award Report Terms [GET /award/api/v1/award-report-terms/]
	 
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

### Get All Award Report Terms with Filtering [GET /award/api/v1/award-report-terms/]
    
+ Parameters

    + awardReportTermId (optional) - Award Report Terms Id. Maximum length is 22.
    + awardId (optional) - 
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + reportClassCode (optional) - Report Class Code. Maximum length is 22.
    + reportCode (optional) - Type. Maximum length is 22.
    + frequencyCode (optional) - Frequency. Maximum length is 22.
    + frequencyBaseCode (optional) - Frequency Base. Maximum length is 22.
    + ospDistributionCode (optional) - OSP File Copy. Maximum length is 22.
    + dueDate (optional) - Due Date. Maximum length is 10.

            
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
			
### Get Schema for Award Report Terms [GET /award/api/v1/award-report-terms/]
	                                          
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
    
            {"columns":["awardReportTermId","awardId","awardNumber","sequenceNumber","reportClassCode","reportCode","frequencyCode","frequencyBaseCode","ospDistributionCode","dueDate"],"primaryKey":"awardReportTermId"}
		
### Get Blueprint API specification for Award Report Terms [GET /award/api/v1/award-report-terms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Report Terms.md"
            transfer-encoding:chunked


### Update Award Report Terms [PUT /award/api/v1/award-report-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Report Terms [PUT /award/api/v1/award-report-terms/]

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

### Insert Award Report Terms [POST /award/api/v1/award-report-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Report Terms [POST /award/api/v1/award-report-terms/]

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
            
### Delete Award Report Terms by Key [DELETE /award/api/v1/award-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Report Terms [DELETE /award/api/v1/award-report-terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Report Terms with Matching [DELETE /award/api/v1/award-report-terms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardReportTermId (optional) - Award Report Terms Id. Maximum length is 22.
    + awardId (optional) - 
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + reportClassCode (optional) - Report Class Code. Maximum length is 22.
    + reportCode (optional) - Type. Maximum length is 22.
    + frequencyCode (optional) - Frequency. Maximum length is 22.
    + frequencyBaseCode (optional) - Frequency Base. Maximum length is 22.
    + ospDistributionCode (optional) - OSP File Copy. Maximum length is 22.
    + dueDate (optional) - Due Date. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
