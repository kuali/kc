## Intellectual Property Reviews [/instprop/api/v1/intellectual-property-reviews/]

### Get Intellectual Property Reviews by Key [GET /instprop/api/v1/intellectual-property-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Reviews [GET /instprop/api/v1/intellectual-property-reviews/]
	 
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

### Get All Intellectual Property Reviews with Filtering [GET /instprop/api/v1/intellectual-property-reviews/]
    
+ Parameters

    + ipReviewer (optional) - Reviewer. Maximum length is 40.
    + ipReviewRequirementTypeCode (optional) - Review Requirement. Maximum length is 22.
    + ipReviewSequenceStatus (optional) - 
    + reviewSubmissionDate (optional) - Submitted for Review. Maximum length is 10.
    + reviewResultCode (optional) - Review Results. Maximum length is 22.
    + reviewReceiveDate (optional) - Date Finished Review. Maximum length is 10.
    + ipReviewId (optional) - 
    + proposalNumber (optional) - Institutional Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - 

            
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
			
### Get Schema for Intellectual Property Reviews [GET /instprop/api/v1/intellectual-property-reviews/]
	                                          
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
    
            {"columns":["ipReviewer","ipReviewRequirementTypeCode","ipReviewSequenceStatus","reviewSubmissionDate","reviewResultCode","reviewReceiveDate","ipReviewId","proposalNumber","sequenceNumber"],"primaryKey":"ipReviewId"}
		
### Get Blueprint API specification for Intellectual Property Reviews [GET /instprop/api/v1/intellectual-property-reviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Reviews.md"
            transfer-encoding:chunked


### Update Intellectual Property Reviews [PUT /instprop/api/v1/intellectual-property-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Reviews [PUT /instprop/api/v1/intellectual-property-reviews/]

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

### Insert Intellectual Property Reviews [POST /instprop/api/v1/intellectual-property-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ipReviewer": "(val)","ipReviewRequirementTypeCode": "(val)","ipReviewSequenceStatus": "(val)","reviewSubmissionDate": "(val)","reviewResultCode": "(val)","reviewReceiveDate": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Reviews [POST /instprop/api/v1/intellectual-property-reviews/]

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
            
### Delete Intellectual Property Reviews by Key [DELETE /instprop/api/v1/intellectual-property-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Reviews [DELETE /instprop/api/v1/intellectual-property-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Reviews with Matching [DELETE /instprop/api/v1/intellectual-property-reviews/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + ipReviewer (optional) - Reviewer. Maximum length is 40.
    + ipReviewRequirementTypeCode (optional) - Review Requirement. Maximum length is 22.
    + ipReviewSequenceStatus (optional) - 
    + reviewSubmissionDate (optional) - Submitted for Review. Maximum length is 10.
    + reviewResultCode (optional) - Review Results. Maximum length is 22.
    + reviewReceiveDate (optional) - Date Finished Review. Maximum length is 10.
    + ipReviewId (optional) - 
    + proposalNumber (optional) - Institutional Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
