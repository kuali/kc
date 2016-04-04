## Intellectual Property Reviews [/research-sys/api/v1/intellectual-property-reviews/]

### Get Intellectual Property Reviews by Key [GET /research-sys/api/v1/intellectual-property-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Reviews [GET /research-sys/api/v1/intellectual-property-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Intellectual Property Reviews with Filtering [GET /research-sys/api/v1/intellectual-property-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + ipReviewer
            + ipReviewRequirementTypeCode
            + ipReviewSequenceStatus
            + reviewSubmissionDate
            + reviewResultCode
            + reviewReceiveDate
            + ipReviewId
            + proposalNumber
            + sequenceNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Intellectual Property Reviews [GET /research-sys/api/v1/intellectual-property-reviews/]
	 
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
		
### Get Blueprint API specification for Intellectual Property Reviews [GET /research-sys/api/v1/intellectual-property-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Reviews.md"
            transfer-encoding:chunked


### Update Intellectual Property Reviews [PUT /research-sys/api/v1/intellectual-property-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Reviews [PUT /research-sys/api/v1/intellectual-property-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Intellectual Property Reviews [POST /research-sys/api/v1/intellectual-property-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Reviews [POST /research-sys/api/v1/intellectual-property-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Intellectual Property Reviews by Key [DELETE /research-sys/api/v1/intellectual-property-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Reviews [DELETE /research-sys/api/v1/intellectual-property-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Intellectual Property Reviews with Matching [DELETE /research-sys/api/v1/intellectual-property-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + ipReviewer
            + ipReviewRequirementTypeCode
            + ipReviewSequenceStatus
            + reviewSubmissionDate
            + reviewResultCode
            + reviewReceiveDate
            + ipReviewId
            + proposalNumber
            + sequenceNumber


+ Response 204
