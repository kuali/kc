## Lite View Iacuc Protocol Submissions [/research-sys/api/v1/lite-view-iacuc-protocol-submissions/]

### Get Lite View Iacuc Protocol Submissions by Key [GET /research-sys/api/v1/lite-view-iacuc-protocol-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}

### Get All Lite View Iacuc Protocol Submissions [GET /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]
	 
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

### Get All Lite View Iacuc Protocol Submissions with Filtering [GET /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
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
            + protocolActive
            + protocolStatusCode
            + protocolTitle
            + piPersonId
            + piPersonName
            + piRolodexId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"},
              {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Lite View Iacuc Protocol Submissions [GET /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]
	 
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
		
### Get Blueprint API specification for Lite View Iacuc Protocol Submissions [GET /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Lite View Iacuc Protocol Submissions.md"
            transfer-encoding:chunked


### Update Lite View Iacuc Protocol Submissions [PUT /research-sys/api/v1/lite-view-iacuc-protocol-submissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Lite View Iacuc Protocol Submissions [PUT /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]

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

### Insert Lite View Iacuc Protocol Submissions [POST /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionId": "(val)","submissionNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","scheduleId": "(val)","committeeId": "(val)","submissionTypeCode": "(val)","submissionTypeQualifierCode": "(val)","submissionStatusCode": "(val)","protocolId": "(val)","scheduleIdFk": "(val)","committeeIdFk": "(val)","protocolReviewTypeCode": "(val)","submissionDate": "(val)","comments": "(val)","committeeDecisionMotionTypeCode": "(val)","yesVoteCount": "(val)","noVoteCount": "(val)","abstainerCount": "(val)","recusedCount": "(val)","votingComments": "(val)","billable": "(val)","protocolActive": "(val)","protocolStatusCode": "(val)","protocolTitle": "(val)","piPersonId": "(val)","piPersonName": "(val)","piRolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Lite View Iacuc Protocol Submissions [POST /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]

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
            
### Delete Lite View Iacuc Protocol Submissions by Key [DELETE /research-sys/api/v1/lite-view-iacuc-protocol-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Iacuc Protocol Submissions [DELETE /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Lite View Iacuc Protocol Submissions with Matching [DELETE /research-sys/api/v1/lite-view-iacuc-protocol-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
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
            + protocolActive
            + protocolStatusCode
            + protocolTitle
            + piPersonId
            + piPersonName
            + piRolodexId


+ Response 204
