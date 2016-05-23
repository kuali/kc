## Award Template Report Terms [/award/api/v1/award-template-report-terms/]

### Get Award Template Report Terms by Key [GET /award/api/v1/award-template-report-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateReportTermId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}

### Get All Award Template Report Terms [GET /award/api/v1/award-template-report-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateReportTermId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Report Terms with Filtering [GET /award/api/v1/award-template-report-terms/]
    
+ Parameters

    + templateReportTermId (optional) - Template Report Terms Id. Maximum length is 22.
    + reportClassCode (optional) - Report Class Code. Maximum length is 3.
    + reportCode (optional) - Report Type Code. Maximum length is 3.
    + frequencyCode (optional) - Frequency Code. Maximum length is 3.
    + frequencyBaseCode (optional) - Frequency Base Code. Maximum length is 3.
    + ospDistributionCode (optional) - OSP File Copy Code. Maximum length is 3.
    + dueDate (optional) - Due Date. Maximum length is 10.
    + awardTemplate.templateCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateReportTermId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateReportTermId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","ospDistributionCode": "(val)","dueDate": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Report Terms [GET /award/api/v1/award-template-report-terms/]
	                                          
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
    
            {"columns":["templateReportTermId","reportClassCode","reportCode","frequencyCode","frequencyBaseCode","ospDistributionCode","dueDate","awardTemplate.templateCode"],"primaryKey":"templateReportTermId"}
		
### Get Blueprint API specification for Award Template Report Terms [GET /award/api/v1/award-template-report-terms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Report Terms.md"
            transfer-encoding:chunked
