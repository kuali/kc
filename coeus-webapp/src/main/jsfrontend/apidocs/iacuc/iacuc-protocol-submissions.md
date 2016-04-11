## Iacuc Protocol Submissions [/research-sys/api/v1/iacuc-protocol-submissions/]

### Get Iacuc Protocol Submissions by Key [GET /research-sys/api/v1/iacuc-protocol-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Submissions [GET /research-sys/api/v1/iacuc-protocol-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Submissions with Filtering [GET /research-sys/api/v1/iacuc-protocol-submissions/]
    
+ Parameters

        + submissionId
            + submissionNumber
            + protocolNumber
            + sequenceNumber
            + scheduleId
            + committeeId
            + submissionTypeCode
            + submissionTypeQualifierCode
            + submissionStatusCode
            + protocolId
            + scheduleIdFk
            + committeeIdFk
            + protocolReviewTypeCode
            + submissionDate
            + comments
            + committeeDecisionMotionTypeCode
            + yesVoteCount
            + noVoteCount
            + abstainerCount
            + recusedCount
            + votingComments
            + billable

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Submissions [GET /research-sys/api/v1/iacuc-protocol-submissions/]
	                                          
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
    
            {"columns":["submissionId","submissionNumber","protocolNumber","sequenceNumber","scheduleId","committeeId","submissionTypeCode","submissionTypeQualifierCode","submissionStatusCode","protocolId","scheduleIdFk","committeeIdFk","protocolReviewTypeCode","submissionDate","comments","committeeDecisionMotionTypeCode","yesVoteCount","noVoteCount","abstainerCount","recusedCount","votingComments","billable"],"primaryKey":"submissionId"}
		
### Get Blueprint API specification for Iacuc Protocol Submissions [GET /research-sys/api/v1/iacuc-protocol-submissions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Submissions.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Submissions [PUT /research-sys/api/v1/iacuc-protocol-submissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Submissions [PUT /research-sys/api/v1/iacuc-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Submissions [POST /research-sys/api/v1/iacuc-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Submissions [POST /research-sys/api/v1/iacuc-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Submissions by Key [DELETE /research-sys/api/v1/iacuc-protocol-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submissions [DELETE /research-sys/api/v1/iacuc-protocol-submissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submissions with Matching [DELETE /research-sys/api/v1/iacuc-protocol-submissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + submissionId
            + submissionNumber
            + protocolNumber
            + sequenceNumber
            + scheduleId
            + committeeId
            + submissionTypeCode
            + submissionTypeQualifierCode
            + submissionStatusCode
            + protocolId
            + scheduleIdFk
            + committeeIdFk
            + protocolReviewTypeCode
            + submissionDate
            + comments
            + committeeDecisionMotionTypeCode
            + yesVoteCount
            + noVoteCount
            + abstainerCount
            + recusedCount
            + votingComments
            + billable

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
