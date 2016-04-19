## Lite View Protocol Submissions [/irb/api/v1/lite-view-protocol-submissions/]

### Get Lite View Protocol Submissions by Key [GET /irb/api/v1/lite-view-protocol-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}

### Get All Lite View Protocol Submissions [GET /irb/api/v1/lite-view-protocol-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Lite View Protocol Submissions with Filtering [GET /irb/api/v1/lite-view-protocol-submissions/]
    
+ Parameters

    + submissionId (optional) - 
    + submissionNumber (optional) - Submission Number. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + scheduleId (optional) - Schedule Id. Maximum length is 10.
    + committeeId (optional) - Committee Id. Maximum length is 15.
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + submissionTypeQualifierCode (optional) - Submission Type Qual Code. Maximum length is 3.
    + submissionStatusCode (optional) - Submission Status Code. Maximum length is 3.
    + protocolId (optional) - 
    + scheduleIdFk (optional) - 
    + committeeIdFk (optional) - Committee Id. Maximum length is 12.
    + protocolReviewTypeCode (optional) - Protocol Review Type Code. Maximum length is 3.
    + submissionDate (optional) - Submission Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.
    + committeeDecisionMotionTypeCode (optional) - Committee Decision Motion Type. Maximum length is 3.
    + yesVoteCount (optional) - Yes Vote Count. Maximum length is 22.
    + noVoteCount (optional) - No Vote Count. Maximum length is 22.
    + abstainerCount (optional) - Abstainer Count. Maximum length is 22.
    + recusedCount (optional) - 
    + votingComments (optional) - Voting Comments. Maximum length is 2000.
    + billable (optional) - Is Billable. Maximum length is 1.
    + protocolActive (optional) - 
    + protocolStatusCode (optional) - 
    + protocolTitle (optional) - Title. Maximum length is 2000.
    + piPersonId (optional) - 
    + piPersonName (optional) - Person Name. Maximum length is 90.
    + piRolodexId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Lite View Protocol Submissions [GET /irb/api/v1/lite-view-protocol-submissions/]
	                                          
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
    
            {"columns":["submissionId","submissionNumber","protocolNumber","sequenceNumber","scheduleId","committeeId","submissionTypeCode","submissionTypeQualifierCode","submissionStatusCode","protocolId","scheduleIdFk","committeeIdFk","protocolReviewTypeCode","submissionDate","comments","committeeDecisionMotionTypeCode","yesVoteCount","noVoteCount","abstainerCount","recusedCount","votingComments","billable","protocolActive","protocolStatusCode","protocolTitle","piPersonId","piPersonName","piRolodexId"],"primaryKey":"submissionId"}
		
### Get Blueprint API specification for Lite View Protocol Submissions [GET /irb/api/v1/lite-view-protocol-submissions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Lite View Protocol Submissions.md"
            transfer-encoding:chunked


### Update Lite View Protocol Submissions [PUT /irb/api/v1/lite-view-protocol-submissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Lite View Protocol Submissions [PUT /irb/api/v1/lite-view-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Lite View Protocol Submissions [POST /irb/api/v1/lite-view-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Lite View Protocol Submissions [POST /irb/api/v1/lite-view-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Lite View Protocol Submissions by Key [DELETE /irb/api/v1/lite-view-protocol-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Protocol Submissions [DELETE /irb/api/v1/lite-view-protocol-submissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Protocol Submissions with Matching [DELETE /irb/api/v1/lite-view-protocol-submissions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + submissionId (optional) - 
    + submissionNumber (optional) - Submission Number. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + scheduleId (optional) - Schedule Id. Maximum length is 10.
    + committeeId (optional) - Committee Id. Maximum length is 15.
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + submissionTypeQualifierCode (optional) - Submission Type Qual Code. Maximum length is 3.
    + submissionStatusCode (optional) - Submission Status Code. Maximum length is 3.
    + protocolId (optional) - 
    + scheduleIdFk (optional) - 
    + committeeIdFk (optional) - Committee Id. Maximum length is 12.
    + protocolReviewTypeCode (optional) - Protocol Review Type Code. Maximum length is 3.
    + submissionDate (optional) - Submission Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.
    + committeeDecisionMotionTypeCode (optional) - Committee Decision Motion Type. Maximum length is 3.
    + yesVoteCount (optional) - Yes Vote Count. Maximum length is 22.
    + noVoteCount (optional) - No Vote Count. Maximum length is 22.
    + abstainerCount (optional) - Abstainer Count. Maximum length is 22.
    + recusedCount (optional) - 
    + votingComments (optional) - Voting Comments. Maximum length is 2000.
    + billable (optional) - Is Billable. Maximum length is 1.
    + protocolActive (optional) - 
    + protocolStatusCode (optional) - 
    + protocolTitle (optional) - Title. Maximum length is 2000.
    + piPersonId (optional) - 
    + piPersonName (optional) - Person Name. Maximum length is 90.
    + piRolodexId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
