## Award Closeouts [/award/api/v1/award-closeouts/]

### Get Award Closeouts by Key [GET /award/api/v1/award-closeouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardCloseoutId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Closeouts [GET /award/api/v1/award-closeouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCloseoutId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Closeouts with Filtering [GET /award/api/v1/award-closeouts/]
    
+ Parameters

    + awardCloseoutId (optional) - Award Closeout Id. Maximum length is 22.
    + closeoutReportCode (optional) - Closeout Report Code. Maximum length is 22.
    + closeoutReportName (optional) - Final Report. Maximum length is 100.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + finalSubmissionDate (optional) - Final Submission Date. Maximum length is 10.
    + dueDate (optional) - Due Date. Maximum length is 10.
    + multiple (optional) - 
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCloseoutId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Closeouts [GET /award/api/v1/award-closeouts/]
	                                          
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
    
            {"columns":["awardCloseoutId","closeoutReportCode","closeoutReportName","awardNumber","sequenceNumber","finalSubmissionDate","dueDate","multiple","award.awardId"],"primaryKey":"awardCloseoutId"}
		
### Get Blueprint API specification for Award Closeouts [GET /award/api/v1/award-closeouts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Closeouts.md"
            transfer-encoding:chunked
